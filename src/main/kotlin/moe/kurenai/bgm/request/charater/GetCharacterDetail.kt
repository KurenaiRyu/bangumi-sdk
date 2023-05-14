package moe.kurenai.bgm.request.charater

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import moe.kurenai.bgm.model.character.CharacterDetail
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

@Serializable
data class GetCharacterDetail(
    @JsonIgnore
    val characterId: Int
): Request<CharacterDetail>() {

    @JsonIgnore
    override val responseDeserializer = CharacterDetail.serializer()

    @JsonIgnore
    override val path: String = "v0/characters/$characterId"

    @JsonIgnore
    @Transient
    override val responseType = object : TypeReference<CharacterDetail>() {}

    @JsonIgnore
    override val httpMethod = HttpMethod.GET
}
