package moe.kurenai.bgm.model.item

import kotlinx.serialization.Serializable
import moe.kurenai.bgm.model.subject.SubjectSmall

@Serializable
data class Calendar(
    val weekday: Weekday,
    val items: List<SubjectSmall>
) {
}
