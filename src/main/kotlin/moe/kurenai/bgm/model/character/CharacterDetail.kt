package moe.kurenai.bgm.model.character

import moe.kurenai.bgm.model.subject.Image

data class CharacterDetail(
    val id: Int,
    val name: String,
    val type: Int,
    val images: Image,
    val summary: String,
    val locked: Boolean,
    val infoBox: Map<String, String>? = null,
    val gender: String,
    val bloodType: Int,
    val birthYear: Int? = null,
    val birthMon: Int? = null,
    val birthDay: Int? = null,
    val stat: Stat
) {

}

data class Stat(
    val comments: Int,
    val collects: Int,
) {

}
