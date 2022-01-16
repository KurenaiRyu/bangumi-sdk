package moe.kurenai.bgm.model.user

data class UserCollection(
    val subjectId: Int,
    val subjectType: Int,
    val rate: Int,
    val type: Int,
    val comment: String? = null,
    val tags: List<String>,
    val epStatus: Int,
    val volStatus: Int,
    val updatedAt: String,
    val private: Boolean,
) {
}
