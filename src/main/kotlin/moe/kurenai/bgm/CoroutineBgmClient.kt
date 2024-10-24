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
import moe.kurenai.bgm.model.auth.AccessToken
import moe.kurenai.bgm.model.error.BgmError
import moe.kurenai.bgm.model.error.NotFoundError
import moe.kurenai.bgm.model.error.UnauthorizedError
import moe.kurenai.bgm.model.error.ValidationError
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
            request.responseDeserializer
                ?: throw BgmException(BgmError("[${request.javaClass.name}]没有声明返回值反序列化对象"))
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
                    .recoverCatching { ex ->
                        json.decodeFromString(BgmError.serializer(), body).let {
                            BgmException(it).apply {
                                cause = ex
                            }
                        }
                    }.recover { ex ->
                        BgmException(BgmError("Unknown response type: $body")).apply { cause = ex }
                    }.getOrThrow()
            }

            HttpStatusCode.Unauthorized -> {
                json.decodeFromString(UnauthorizedError.serializer(), body)
            }

            HttpStatusCode.NotFound -> {
                json.decodeFromString(NotFoundError.serializer(), body)
            }

            HttpStatusCode.UnprocessableEntity -> {
                json.decodeFromString(ValidationError.serializer(), body)
            }

            else -> {
                runCatching {
                    json.decodeFromString(BgmError.serializer(), body)
                }.recover {
                    BgmException(BgmError(error = "Unknown response type: $body")).apply {
                        this.cause = it
                    }
                }.getOrThrow()
            }
        }.let {
            return@let when (it) {
                is BgmError -> throw BgmException(it)
                is BgmException -> throw it
                is Throwable -> throw BgmException(BgmError("Unknown response type: $body")).apply { cause = it }
                else -> it as T
            }
        }
    }

    private fun Request<*>.getUrl(): String {
        return if (this.isAuthRequest) "${default.authBaseUrl}/${this.path}" else "${default.apiBaseUrl}/${this.path}"
    }

}
