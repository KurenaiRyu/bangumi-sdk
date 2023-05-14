package moe.kurenai.bgm.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.kurenai.bgm.model.subject.SlimSubject

@Serializable
data class UserCollection(
    @SerialName("subject_id")
    val subjectId: Int,
    @SerialName("subject_type")
    val subjectType: Int,
    val subject: SlimSubject?,
    val rate: Int,
    val type: Int,
    val comment: String?,
    val tags: List<String>,
    @SerialName("ep_status")
    val epStatus: Int,
    @SerialName("vol_status")
    val volStatus: Int,
    @SerialName("updated_at")
    val updatedAt: String,
    val private: Boolean,
)
