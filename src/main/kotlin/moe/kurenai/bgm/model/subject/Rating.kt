package moe.kurenai.bgm.model.subject

import kotlinx.serialization.Serializable

@Serializable
class Rating {
    var total: Int = 0
    var count: Map<Int, Int>? = null
    var score: Float = 0.0F
}
