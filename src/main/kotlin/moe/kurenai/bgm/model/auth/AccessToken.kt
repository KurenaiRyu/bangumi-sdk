package moe.kurenai.bgm.model.auth

class AccessToken(
    val accessToken: String,
    val expiresIn: Long,
    val tokenType: String,
    val scope: String? = null,
    val refreshToken: String,
    val userId: Long
)

object AccessTokenType {
    const val BEARER = "Bearer"
}
