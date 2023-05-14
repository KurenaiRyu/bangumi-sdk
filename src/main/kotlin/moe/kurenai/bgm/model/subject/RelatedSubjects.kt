package moe.kurenai.bgm.model.subject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RelatedSubjects(
    val id: Int,
    val staff: String,
    val name: String?,
    @SerialName("name_cn")
    val nameCn: String,
    val image: String?,
)
