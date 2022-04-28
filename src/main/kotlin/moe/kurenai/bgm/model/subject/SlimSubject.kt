package moe.kurenai.bgm.model.subject

import moe.kurenai.bgm.model.Tag


data class SlimSubject(
    val id: Int = 0,
    val type: Int = 0,
    val name: String = "",
    val nameCn: String = "",
    val shortSummary: String = "",
    val date: String?,
    val images: Image? = null,
    val eps: Int = 0,
    val volumes: Int = 0,
    val score: Float? = null,
    val collectionTotal: Int = 0,
    val tags: List<Tag>? = null,
)
