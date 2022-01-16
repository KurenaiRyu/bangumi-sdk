package moe.kurenai.bgm

import moe.kurenai.bgm.exception.BgmException
import moe.kurenai.bgm.model.auth.AccessToken
import moe.kurenai.bgm.request.auth.AccessTokenGrantType
import moe.kurenai.bgm.request.auth.AccessTokenRequest
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request
import moe.kurenai.bgm.util.DefaultMapper
import moe.kurenai.bgm.util.DefaultMapper.convertToMap
import moe.kurenai.bgm.util.DefaultMapper.parse
import moe.kurenai.bgm.util.HttpHeaders
import org.apache.logging.log4j.LogManager
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.util.concurrent.CompletableFuture
import kotlin.jvm.Throws

class BgmClient(
    val appId: String,
    val appSecret: String,
    val redirectUri: String,
    val apiBaseUrl: String = "https://api.bgm.tv",
    val authBaseUrl: String = "https://bgm.tv/oauth",
    val isDebugEnabled: Boolean = false
) {

    companion object {
        private val log = LogManager.getLogger()
        private val DEFAULT_TIMEOUT = Duration.ofSeconds(10)
        private const val DEFAULT_MIME_TYPE = "application/json"
    }

    fun <T> send(request: Request<T>, timeout: Duration? = null): CompletableFuture<T> {
        return HttpClient.newHttpClient()
            .sendAsync(buildRequest(request, timeout), HttpResponse.BodyHandlers.ofByteArray())
            .thenApplyAsync { response: HttpResponse<ByteArray> -> response.log() }
            .thenApply { response: HttpResponse<ByteArray> -> response.parse(request.responseType) }
            .handle { res, case ->
                case?.let {
                    log.error("Bangumi client error", case)
                    throw case
                }
                res
            }
    }

    fun <T> sendSync(request: Request<T>, timeout: Duration? = null): T {
        return HttpClient.newHttpClient()
            .send(buildRequest(request, timeout), HttpResponse.BodyHandlers.ofByteArray())
            .log()
            .parse(request.responseType)
    }

    fun oauthUrl(state: String? = null): String {
        val url = "$authBaseUrl/authorize?client_id=$appId&response_type=code&redirect_uri=$redirectUri"
        return state?.let { "$url&state=$state" }?:url
    }

    fun getToken(code: String, state: String? = null): CompletableFuture<AccessToken> {
        return send(AccessTokenRequest(AccessTokenGrantType.AUTHORIZATION_CODE, appId, appSecret, redirectUri, state = state, code = code))
    }

    fun refreshToken(refreshToken: String): CompletableFuture<AccessToken> {
        return send(AccessTokenRequest(AccessTokenGrantType.REFRESH_TOKEN, appId, appSecret, redirectUri, refreshToken = refreshToken))
    }

    @Throws(BgmException::class)
    private fun <T> buildRequest(request: Request<T>, timeout: Duration? = null): HttpRequest? {
        var uri = determineUri(request)
        val httpRequest = HttpRequest.newBuilder()
        request.token?.let {
            httpRequest.header(HttpHeaders.AUTHORIZATION, "Bearer ${request.token}")
        }
        if (request.httpMethod == HttpMethod.POST) {
            httpRequest
                .headers(HttpHeaders.CONTENT_TYPE, DEFAULT_MIME_TYPE)
                .headers(HttpHeaders.ACCEPT, DEFAULT_MIME_TYPE)
                .POST(HttpRequest.BodyPublishers.ofByteArray(DefaultMapper.convertToByteArray(request).also { printDebugRequest(it) }))
        } else {
            val map = convertToMap(request)
            if (map.isNotEmpty()) {
                val params = URLEncoder.encode(map.map { "${it.key}=${it.value}" }.joinToString("&"), StandardCharsets.UTF_8)
                uri = "$uri?$params"
            }
            httpRequest.GET()
        }
        if (timeout != Duration.ZERO) httpRequest.timeout(timeout ?: DEFAULT_TIMEOUT)
        if (isDebugEnabled) log.debug("Request to $uri")
        return httpRequest.uri(URI.create(uri)).build()
    }

    private fun printDebugRequest(byteArray: ByteArray) {
        if (!isDebugEnabled) return
        log.debug("Request data \n{}", String(byteArray))
    }

    private fun determineUri(request: Request<*>): String {
        return if (request.isAuthRequest) "$authBaseUrl/${request.method}" else "$apiBaseUrl/${request.method}"
    }

    private fun logResponse(response: HttpResponse<ByteArray>): HttpResponse<ByteArray> {
        if (isDebugEnabled) response.body()?.let { log.debug("Response ${String(it)}") }
        return response
    }

    private fun HttpResponse<ByteArray>.log(): HttpResponse<ByteArray> {
        return logResponse(this)
    }

}
