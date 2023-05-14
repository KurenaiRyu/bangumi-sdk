package moe.kurenai.bgm.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class AccessToken(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("expires_in")
    val expiresIn: Long,
    @SerialName("token_type")
    val tokenType: String,
    val scope: String? = null,
    @SerialName("user_id")
    val userId: Long,
    @SerialName("refresh_token")
    val refreshToken: String
)

object AccessTokenType {
    const val BEARER = "Bearer"
}
