package moe.kurenai.bgm.model.item

data class RelatedSubject (
    val id: Int,
    val type: Int,
    val name: String,
    val nameCn: String,
    val career: String,
    val images: Image,
    val relation: String,
    ) {

}
