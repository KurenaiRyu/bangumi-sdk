package moe.kurenai.bgm.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Me(
    val avatar: Avatar,
    val sign: String,
    val username: String,
    val nickname: String,
    val id: Int,
    @SerialName("user_group")
    val userGroup: Int
) {
    @Serializable
    data class Avatar(
        val large: String,
        val medium: String,
        val small: String
    )
}
