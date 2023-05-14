package moe.kurenai.bgm.model.subject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.kurenai.bgm.model.Tag

@Serializable
data class SlimSubject(
    val id: Int = 0,
    val type: Int = 0,
    val name: String = "",
    @SerialName("name_cn")
    val nameCn: String = "",
    @SerialName("short_summary")
    val shortSummary: String = "",
    val date: String?,
    val images: Image? = null,
    val eps: Int = 0,
    val volumes: Int = 0,
    val score: Float? = null,
    @SerialName("collection_total")
    val collectionTotal: Int = 0,
    val tags: List<Tag>? = null,
)
