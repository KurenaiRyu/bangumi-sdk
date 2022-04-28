package moe.kurenai.bgm.model.character

import com.fasterxml.jackson.annotation.JsonProperty
import moe.kurenai.bgm.model.subject.Image

data class CharacterPerson(
    val id: Int,
    @JsonProperty("Name") val name: String,
    val type: Int, //角色1，机体2，舰船3，组织4
    val images: Image?,
    val subjectId: Int,
    val subjectName: String,
    val subjectNameCn: String,
)
