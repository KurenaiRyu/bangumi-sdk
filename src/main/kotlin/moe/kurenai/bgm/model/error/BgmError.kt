package moe.kurenai.bgm.model.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BgmError(
    val error: String = "",
    @SerialName("error_description")
    val errorDescription: String = "",
    val request: String = "",
    val code: Int = 0,
)
