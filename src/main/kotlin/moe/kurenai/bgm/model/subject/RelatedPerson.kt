package moe.kurenai.bgm.model.subject

import kotlinx.serialization.Serializable

@Serializable
data class RelatedPerson(
    val id: Int,
    val name: String,
    val type: Int,
    val career: List<String>,
    val images: Image?,
    val relation: String,
)
