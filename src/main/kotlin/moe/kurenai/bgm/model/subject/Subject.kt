package moe.kurenai.bgm.model.subject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.kurenai.bgm.model.InfoBox
import moe.kurenai.bgm.model.Tag

@Serializable
data class Subject(
    val id: Int = 0,
    val type: Int = 0,
    val name: String = "",
    @SerialName("name_cn")
    val nameCn: String = "",
    val summary: String = "",
    val nsfw: Boolean = false,
    val locked: Boolean = false,
    val date: String? = "",
    val platform: String? = null,
    val images: Image? = null,
    val infobox: List<InfoBox>? = null,
    val volumes: Int = 0,
    val eps: Int = 0,
    @SerialName("total_episodes")
    val totalEpisodes: Int = 0,
    val rating: Rating? = null,
    val collection: SubjectCollection? = null,
    val tags: List<Tag>? = null,
)
