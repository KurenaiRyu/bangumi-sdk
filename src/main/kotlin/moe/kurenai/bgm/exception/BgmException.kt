package moe.kurenai.bgm.exception

open class BgmException(
    val error: String = "",
    val request: String = "",
    val code: Int = 0,
) : RuntimeException(error) {
    override var cause: Throwable? = null
}
