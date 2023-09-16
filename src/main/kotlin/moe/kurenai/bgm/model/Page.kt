package moe.kurenai.bgm.model

import kotlinx.serialization.Serializable

@Serializable
data class Page<T>(
    val total: Int,
    val limit: Int,
    val offset: Int,
    val data: List<T>,
) {


}
