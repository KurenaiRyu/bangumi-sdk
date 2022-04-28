package moe.kurenai.bgm.model.person

import com.fasterxml.jackson.databind.JsonNode
import moe.kurenai.bgm.model.subject.Image

class PersonDetail(
    val id: Int,
    val name: String,
    val type: Int,
    val career: List<String>,
    val images: Image?,
    val summary: String,
    val locked: Boolean,
    val lastModified: String,
    val infobox: JsonNode? = null,
    val bloodType: Int,
    val gender: String? = null,
    val birthYear: Int? = null,
    val birthMon: Int? = null,
    val birthDay: Int? = null,
    val stat: Stat,
) {
}

data class Stat(
    val comments: Int,
    val collects: Int,
)
