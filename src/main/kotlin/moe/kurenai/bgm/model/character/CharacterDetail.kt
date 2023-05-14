package moe.kurenai.bgm.model.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.kurenai.bgm.model.InfoBox
import moe.kurenai.bgm.model.Stat
import moe.kurenai.bgm.model.subject.Image

@Serializable
data class CharacterDetail(
    val id: Int,
    val name: String,
    val type: Int,
    val images: Image?,
    val summary: String,
    val locked: Boolean,
    val infobox: List<InfoBox>? = null,
    @SerialName("blood_type")
    val bloodType: Int? = null,
    val gender: String? = null,
    @SerialName("birth_year")
    val birthYear: Int? = null,
    @SerialName("birth_mon")
    val birthMon: Int? = null,
    @SerialName("birth_day")
    val birthDay: Int? = null,
    val stat: Stat
)
