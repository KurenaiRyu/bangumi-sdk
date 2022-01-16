package moe.kurenai.bgm.exception

open class BgmException(
    override var message: String? = null
): RuntimeException() {
    override var cause: Throwable? = null
}
