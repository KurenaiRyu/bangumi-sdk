package moe.kurenai.bgm.model.person

import moe.kurenai.bgm.model.item.Image
import moe.kurenai.bgm.request.PersonCareer

data class CharacterDetail(
    val id: Int,
    val name: String,
    val type: Int,
    val career: String,
    val images: Image,
    val summary: String,
    val locked: Boolean,
    val lastModified: String,
    val infoBox: Map<String, String>? = null,
    val gender: String,
    val bloodType: Int,
    val birthYear: Int? = null,
    val birthMon: Int? = null,
    val birthDay: Int? = null,
    val stat: Stat,
    val img: Image,
) {

}

data class Stat(
    val comments: Int,
    val collects: Int,
) {

}
