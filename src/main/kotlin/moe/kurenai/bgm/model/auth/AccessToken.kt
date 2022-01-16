package moe.kurenai.bgm.model.auth

class AccessToken(
    var accessToken: String,
    var expiresIn: Long,
    var tokenType: String,
    var scope: String? = null,
    var refreshToken: String,
    var userId: Long
)

object AccessTokenType {
    const val BEARER = "Bearer"
}
