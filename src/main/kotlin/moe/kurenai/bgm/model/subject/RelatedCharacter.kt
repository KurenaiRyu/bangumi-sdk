package moe.kurenai.bgm.model.subject

data class RelatedCharacter(
    val id: Int,
    val name: String,
    val type: Int,
    val career: String,
    val images: Image,
    val relation: String,
) {

}
