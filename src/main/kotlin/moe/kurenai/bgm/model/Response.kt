package moe.kurenai.bgm.model

data class Response (
    val request: String,
    val code: Int,
    val error: String,
) {
}
