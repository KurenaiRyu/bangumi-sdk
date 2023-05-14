package moe.kurenai.bgm.model.subject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RelatedSubject(
    val id: Int,
    val type: Int,
    val name: String,
    @SerialName("name_cn")
    val nameCn: String,
    val career: String,
    val images: Image?,
    val relation: String,
) {

}
