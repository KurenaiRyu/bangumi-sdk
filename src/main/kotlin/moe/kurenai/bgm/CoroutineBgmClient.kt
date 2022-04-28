package moe.kurenai.bgm

import com.fasterxml.jackson.core.JacksonException
import com.fasterxml.jackson.core.type.TypeReference
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import moe.kurenai.bgm.exception.BgmException
import moe.kurenai.bgm.exception.NotFoundException
import moe.kurenai.bgm.exception.UnauthorizedException
import moe.kurenai.bgm.exception.ValidationError
import moe.kurenai.bgm.model.auth.AccessToken
import moe.kurenai.bgm.request.Request
import moe.kurenai.bgm.request.auth.AccessTokenGrantType
import moe.kurenai.bgm.util.DefaultMapper.MAPPER
import moe.kurenai.bgm.util.DefaultMapper.convertToByteArray
import moe.kurenai.bgm.util.DefaultMapper.convertToMap
import org.apache.logging.log4j.LogManager

class CoroutineBgmClient(
    val default: BgmClient
) {

    companion object {
        private val log = LogManager.getLogger()
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
        return resp.parse(request.responseType)
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

    private suspend fun <T> HttpResponse.parse(reference: TypeReference<T>): T {
        val body = this.body<ByteArray>()
        return when (this.status) {
            HttpStatusCode.OK -> {
                kotlin.runCatching { MAPPER.readValue(body, reference) }
                    .recover {
                        throw if (it is JacksonException) it
                        else kotlin.runCatching { MAPPER.readValue(body, BgmException::class.java) }.getOrNull() ?: it
                    }.getOrThrow()
            }

            HttpStatusCode.Unauthorized -> {
                throw MAPPER.readValue(body, UnauthorizedException::class.java)
            }

            HttpStatusCode.NotFound -> {
                throw MAPPER.readValue(body, NotFoundException::class.java)
            }

            HttpStatusCode.UnprocessableEntity -> {
                throw MAPPER.readValue(body, ValidationError::class.java)
            }

            else -> {
                throw BgmException("Unknown response type")
            }
        }
    }

    private fun Request<*>.getUrl(): String {
        return if (this.isAuthRequest) "${default.authBaseUrl}/${this.path}" else "${default.apiBaseUrl}/${this.path}"
    }

}
