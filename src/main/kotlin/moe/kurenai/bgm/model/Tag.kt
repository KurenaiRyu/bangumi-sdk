package moe.kurenai.bgm.model

import kotlinx.serialization.Serializable

/**
 * @author Kurenai
 * @since 2022/10/22 17:07
 */

@Serializable
data class Tag(
    val name: String,
    val count: Int
)
