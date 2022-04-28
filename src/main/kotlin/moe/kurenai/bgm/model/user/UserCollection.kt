package moe.kurenai.bgm.model.user

import moe.kurenai.bgm.model.subject.SlimSubject

data class UserCollection(
    val subjectId: Int,
    val subjectType: Int,
    val subject: SlimSubject?,
    val rate: Int,
    val type: Int,
    val comment: String?,
    val tags: List<String>,
    val epStatus: Int,
    val volStatus: Int,
    val updatedAt: String,
    val private: Boolean,
)
