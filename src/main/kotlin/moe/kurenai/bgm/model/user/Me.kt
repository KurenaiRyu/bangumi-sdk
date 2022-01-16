package moe.kurenai.bgm.model.user

data class Me (
    val id: Int,
    val url: String,
    val username: String,
    val nickname: String,
    val userGroup: Int,
    val avatar: Avatar,
    val sign: String,
)
