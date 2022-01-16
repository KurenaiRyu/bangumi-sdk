package moe.kurenai.bgm.model.item

data class Calendar(
    val weekday: Weekday,
    val items: List<SubjectSmall>
) {
}
