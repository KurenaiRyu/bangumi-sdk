package moe.kurenai.bgm.exception

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
open class BgmException(
    val error: String = "",
    @SerialName("error_description")
    val errorDescription: String = "",
    val request: String = "",
    val code: Int = 0,
) : RuntimeException("$error - $errorDescription") {
    @Transient
    override var cause: Throwable? = null
}
