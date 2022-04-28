package moe.kurenai.bgm.model.item

import moe.kurenai.bgm.model.subject.SubjectSmall

data class Calendar(
    val weekday: Weekday,
    val items: List<SubjectSmall>
) {
}
