package moe.kurenai.bgm.model.subject

import kotlinx.serialization.Serializable

@Serializable
data class Calendar(
    val weekday: Weekday,
    val items: List<SubjectSmall>
) {
}
