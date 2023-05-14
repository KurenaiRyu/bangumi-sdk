package moe.kurenai.bgm.model.item

import kotlinx.serialization.Serializable

@Serializable
data class Weekday(
    val en: String,
    val cn: String,
    val ja: String,
    val id: String,
)
