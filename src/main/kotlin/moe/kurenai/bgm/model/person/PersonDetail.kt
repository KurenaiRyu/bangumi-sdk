package moe.kurenai.bgm.model.person

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.kurenai.bgm.model.InfoBox
import moe.kurenai.bgm.model.Stat
import moe.kurenai.bgm.model.subject.Image

@Serializable
data class PersonDetail(
    val id: Int,
    val name: String,
    val type: Int,
    val career: List<String>,
    val images: Image,
    val summary: String,
    val locked: Boolean,
    @SerialName("last_modified")
    val lastModified: String,
    val infobox: List<InfoBox>,
    val gender: String,
    @SerialName("blood_type")
    val bloodType: Int?,
    @SerialName("birth_year")
    val birthYear: Int,
    @SerialName("birth_mon")
    val birthMon: Int,
    @SerialName("birth_day")
    val birthDay: Int,
    val stat: Stat
)
