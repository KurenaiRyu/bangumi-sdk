package moe.kurenai.bgm.model.item

data class RelatedPerson (
    val id: Int,
    val name: String,
    val type: Int,
    val career: String,
    val images: Image,
    val relation: String,
    ) {

}
