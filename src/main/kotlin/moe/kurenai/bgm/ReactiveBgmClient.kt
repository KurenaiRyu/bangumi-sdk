package moe.kurenai.bgm

import moe.kurenai.bgm.model.auth.AccessToken
import moe.kurenai.bgm.request.Request
import moe.kurenai.bgm.request.auth.AccessTokenGrantType
import moe.kurenai.bgm.util.DefaultMapper.parse
import org.apache.logging.log4j.LogManager
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.net.http.HttpResponse
import java.time.Duration

class ReactiveBgmClient(
    val default: BgmClient
) {

    companion object {
        private val log = LogManager.getLogger()
    }

    fun <T> send(request: Request<T>, timeout: Duration? = null): Mono<T> {
        return Mono.fromFuture(default.client.sendAsync(default.buildRequest(request, timeout), HttpResponse.BodyHandlers.ofByteArray()))
            .publishOn(Schedulers.boundedElastic())
            .subscribeOn(Schedulers.parallel())
            .doOnNext(default::logResponse)
            .map { it.parse(request.responseType) }
            .onErrorResume {
                log.error("Bangumi Reactive Client error", it)
                throw it
            }
    }

    fun getOauthUrl(state: String? = null): String {
        return default.getOauthUrl(state)
    }

    fun getToken(code: String, state: String? = null): Mono<AccessToken> {
        return send(default.buildAccessTokenReq(AccessTokenGrantType.AUTHORIZATION_CODE, code, state = state))
    }

    fun refreshToken(refreshToken: String): Mono<AccessToken> {
        return send(default.buildAccessTokenReq(AccessTokenGrantType.REFRESH_TOKEN, refreshToken = refreshToken))
    }

    fun sync() = default

    fun async() = default.async()

    fun coroutine() = default.coroutine()

}
