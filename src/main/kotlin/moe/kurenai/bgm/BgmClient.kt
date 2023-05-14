package moe.kurenai.bgm

import moe.kurenai.bgm.exception.BgmException
import moe.kurenai.bgm.model.auth.AccessToken
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request
import moe.kurenai.bgm.request.auth.AccessTokenGrantType
import moe.kurenai.bgm.request.auth.AccessTokenRequest
import moe.kurenai.bgm.util.BgmUtils.urlEncode
import moe.kurenai.bgm.util.DefaultMapper
import moe.kurenai.bgm.util.DefaultMapper.convertToMap
import moe.kurenai.bgm.util.DefaultMapper.parse
import moe.kurenai.bgm.util.HttpHeaders
import moe.kurenai.bgm.util.getLogger
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration

class BgmClient(
    val appId: String,
    val appSecret: String,
    val redirectUri: String = "",
    val apiBaseUrl: String = "https://api.bgm.tv",
    val authBaseUrl: String = "https://bgm.tv/oauth",
    val isDebugEnabled: Boolean = false
) {

    companion object {
        internal val OS_NAME = System.getProperty("os.name")
        internal val OS_ARCH = System.getProperty("os.arch")
        internal val OS_VERSION = System.getProperty("os.version")
        internal val UA = "Kurenai Bangumi SDK Client/0.0.1 ($OS_NAME $OS_ARCH $OS_VERSION)"
        internal val DEFAULT_TIMEOUT = Duration.ofSeconds(10)
        internal const val DEFAULT_MIME_TYPE = "application/json"

        private val log = getLogger()
    }

    internal val reactiveClient: ReactiveBgmClient by lazy { ReactiveBgmClient(this) }
    internal val asyncClient: AsyncBgmClient by lazy { AsyncBgmClient(this) }
    internal val coroutineClient: CoroutineBgmClient by lazy { CoroutineBgmClient(this) }

    internal val client = HttpClient.newHttpClient()

    fun <T> send(request: Request<T>, timeout: Duration? = null): T {
        return client.send(buildRequest(request, timeout), HttpResponse.BodyHandlers.ofByteArray())
            .log()
            .parse(request.responseType)
    }

    fun getOauthUrl(state: String? = null): String {
        val url = "$authBaseUrl/authorize?client_id=$appId&response_type=code&redirect_uri=$redirectUri"
        return state?.let { "$url&state=$state" } ?: url
    }

    fun getToken(code: String, state: String? = null): AccessToken {
        return send(buildAccessTokenReq(AccessTokenGrantType.AUTHORIZATION_CODE, code, state = state))
    }

    fun refreshToken(refreshToken: String): AccessToken {
        return send(buildAccessTokenReq(AccessTokenGrantType.REFRESH_TOKEN, refreshToken = refreshToken))
    }

    fun reactive(): ReactiveBgmClient {
        return reactiveClient
    }

    fun async(): AsyncBgmClient {
        return asyncClient
    }

    fun coroutine(): CoroutineBgmClient {
        return coroutineClient
    }

    @Throws(BgmException::class)
    internal fun <T> buildRequest(request: Request<T>, timeout: Duration? = null): HttpRequest? {
        var uri = determineUri(request)
        val httpRequest = HttpRequest.newBuilder()
        httpRequest.header(HttpHeaders.UA, UA)
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
                val params = map.map { "${it.key}=${it.value.toString().urlEncode()}" }.joinToString("&")
                uri = "$uri?$params"
            }
            httpRequest.GET()
        }
        if (timeout != Duration.ZERO) httpRequest.timeout(timeout ?: DEFAULT_TIMEOUT)
        if (isDebugEnabled) log.debug("Request to $uri")
        return httpRequest.uri(URI.create(uri)).build()
    }

    internal fun buildAccessTokenReq(grantType: String, code: String? = null, refreshToken: String? = null, state: String? = null): AccessTokenRequest {
        return AccessTokenRequest(grantType, appId, appSecret, redirectUri, code, refreshToken, state)
    }

    internal fun determineUri(request: Request<*>): String {
        return if (request.isAuthRequest) "$authBaseUrl/${request.path}" else "$apiBaseUrl/${request.path}"
    }

    internal fun printDebugRequest(byteArray: ByteArray) {
        if (!isDebugEnabled) return
        log.debug("Request data \n{}", String(byteArray))
    }

    internal fun logResponse(response: HttpResponse<ByteArray>): HttpResponse<ByteArray> {
        if (isDebugEnabled) response.body()?.let { log.debug("Response ${String(it)}") }
        return response
    }

    internal fun HttpResponse<ByteArray>.log(): HttpResponse<ByteArray> {
        return logResponse(this)
    }

}
