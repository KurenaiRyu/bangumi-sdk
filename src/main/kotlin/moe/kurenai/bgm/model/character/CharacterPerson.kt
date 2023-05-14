package moe.kurenai.bgm.model.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.kurenai.bgm.model.subject.Image

@Serializable
data class CharacterPerson(
    val id: Int,
    val name: String,
    val type: Int, //角色1，机体2，舰船3，组织4
    @SerialName("subject_id")
    val subjectId: Int,
    @SerialName("subject_name")
    val subjectName: String,
    @SerialName("subject_name_cn")
    val subjectNameCn: String,
    val staff: String? = null,
    val images: Image? = null,
)
