package moe.kurenai.bgm.model.subject

data class Calendar(
    val weekday: Weekday,
    val items: List<SubjectSmall>
) {
}
