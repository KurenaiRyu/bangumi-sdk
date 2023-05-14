package moe.kurenai.bgm

import moe.kurenai.bgm.model.auth.AccessToken
import moe.kurenai.bgm.request.Request
import moe.kurenai.bgm.request.auth.AccessTokenGrantType
import moe.kurenai.bgm.util.DefaultMapper.parse
import moe.kurenai.bgm.util.getLogger
import java.net.http.HttpResponse
import java.time.Duration
import java.util.concurrent.CompletableFuture

class AsyncBgmClient(
    val default: BgmClient
) {

    companion object {
        private val log = getLogger()
    }

    fun <T> send(request: Request<T>, timeout: Duration? = null): CompletableFuture<T> {
        return default.client
            .sendAsync(default.buildRequest(request, timeout), HttpResponse.BodyHandlers.ofByteArray())
            .thenApplyAsync { response: HttpResponse<ByteArray> -> response.log() }
            .thenApply { response: HttpResponse<ByteArray> -> response.parse(request.responseType) }
            .handle { res, case ->
                case?.let {
                    log.error("Bangumi Async Client error", case)
                    throw case
                }
                res
            }
    }

    fun getOauthUrl(state: String? = null): String {
        return default.getOauthUrl(state)
    }

    fun getToken(code: String, state: String? = null): CompletableFuture<AccessToken> {
        return send(default.buildAccessTokenReq(AccessTokenGrantType.AUTHORIZATION_CODE, code, state = state))
    }

    fun refreshToken(refreshToken: String): CompletableFuture<AccessToken> {
        return send(default.buildAccessTokenReq(AccessTokenGrantType.REFRESH_TOKEN, refreshToken = refreshToken))
    }

    fun sync() = default

    fun reactive() = default.reactive()

    fun coroutine() = default.coroutine()

    private fun HttpResponse<ByteArray>.log(): HttpResponse<ByteArray> {
        return default.logResponse(this)
    }

}
