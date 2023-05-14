package moe.kurenai.bgm

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import moe.kurenai.bgm.exception.BgmException
import moe.kurenai.bgm.exception.NotFoundException
import moe.kurenai.bgm.exception.UnauthorizedException
import moe.kurenai.bgm.exception.ValidationError
import moe.kurenai.bgm.model.auth.AccessToken
import moe.kurenai.bgm.request.Request
import moe.kurenai.bgm.request.auth.AccessTokenGrantType
import moe.kurenai.bgm.util.DefaultMapper.convertToByteArray
import moe.kurenai.bgm.util.DefaultMapper.convertToMap
import moe.kurenai.bgm.util.getLogger

class CoroutineBgmClient(
    val default: BgmClient
) {

    companion object {
        private val log = getLogger()
        private val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = true
        }
    }

    val client = HttpClient(OkHttp) {
        install(ContentEncoding) {
            deflate(1.0F)
            gzip(0.9F)
        }
    }

    suspend fun <T> send(request: Request<T>): T {
        val resp = client.request {
            url {
                url.takeFrom(request.getUrl())
                if (request.httpMethod == moe.kurenai.bgm.request.HttpMethod.GET) {
                    method = HttpMethod.Get
                    for (entry in convertToMap(request)) {
                        parameters.append(entry.key.toString(), entry.value.toString())
                    }
                } else {
                    method = HttpMethod.Post
                    setBody(convertToByteArray(request))
                }
            }
            headers {
                append(HttpHeaders.Accept, BgmClient.DEFAULT_MIME_TYPE)
                append(HttpHeaders.ContentType, BgmClient.DEFAULT_MIME_TYPE)
                append(HttpHeaders.UserAgent, BgmClient.UA)
                request.token?.let { append(HttpHeaders.Authorization, "Bearer ${request.token}") }
            }
        }
        val deserializer =
            request.responseDeserializer ?: throw BgmException("[${request.javaClass.name}]没有声明返回值反序列化对象")
        return resp.parse(deserializer)
    }

    fun getOauthUrl(state: String? = null): String {
        return default.getOauthUrl(state)
    }

    suspend fun getToken(code: String, state: String? = null): AccessToken {
        return send(default.buildAccessTokenReq(AccessTokenGrantType.AUTHORIZATION_CODE, code, state = state))
    }

    suspend fun refreshToken(refreshToken: String): AccessToken {
        return send(default.buildAccessTokenReq(AccessTokenGrantType.REFRESH_TOKEN, refreshToken = refreshToken))
    }

    fun sync() = default

    fun reactive() = default.reactive()

    fun async() = default.async()

    private suspend fun <T> HttpResponse.parse(deserializer: DeserializationStrategy<T>): T {
        val body = this.bodyAsText()
        return when (this.status) {
            HttpStatusCode.OK -> {
                kotlin.runCatching { json.decodeFromString(deserializer, body) }
                    .recover {
                        throw kotlin.runCatching {
                            json.decodeFromString(BgmException.serializer(), body).apply {
                                cause = it
                            }
                        }.getOrNull() ?: it
                    }.getOrThrow()
            }

            HttpStatusCode.Unauthorized -> {
                throw json.decodeFromString(UnauthorizedException.serializer(), body)
            }

            HttpStatusCode.NotFound -> {
                throw json.decodeFromString(NotFoundException.serializer(), body)
            }

            HttpStatusCode.UnprocessableEntity -> {
                throw json.decodeFromString(ValidationError.serializer(), body)
            }

            else -> {
                throw runCatching {
                    json.decodeFromString(BgmException.serializer(), body)
                }.recover {
                    BgmException("Unknown response type")
                }.getOrThrow()
            }
        }
    }

    private fun Request<*>.getUrl(): String {
        return if (this.isAuthRequest) "${default.authBaseUrl}/${this.path}" else "${default.apiBaseUrl}/${this.path}"
    }

}
