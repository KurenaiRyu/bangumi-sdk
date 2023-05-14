package moe.kurenai.bgm.model

import kotlinx.serialization.Serializable

@Serializable
data class Stat(
    val comments: Int,
    val collects: Int,
)
