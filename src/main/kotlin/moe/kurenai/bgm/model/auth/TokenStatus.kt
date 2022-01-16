package moe.kurenai.bgm.model.auth

data class TokenStatus (
    val accessToken: String,
    val clientId: String,
    val expires: Long,
    val userId: Long,
    val scope: String? = null,
)
