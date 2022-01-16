package moe.kurenai.bgm.model.item

data class SubjectSmall(
    val id: Int,
    val url: String,
    val type: Int,
    val name: String,
    val nameCn: String,
    val summary: String,
    val airDate: String,
    val airWeekday: Int,
    val images: Image? = null,
    val eps: Int,
    val epsCount: Int,
    val rating: Rating? = null,
    val collection: SubjectCollection? = null,
) {
}
