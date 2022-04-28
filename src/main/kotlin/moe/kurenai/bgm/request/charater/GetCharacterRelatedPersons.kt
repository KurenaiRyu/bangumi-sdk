package moe.kurenai.bgm.request.charater

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.character.CharacterPerson
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

data class GetCharacterRelatedPersons(
    @JsonIgnore
    val characterId: Int,
) : Request<List<CharacterPerson>>() {

    @JsonIgnore
    override val path: String = "v0/characters/$characterId/persons"

    @JsonIgnore
    override val responseType = object : TypeReference<List<CharacterPerson>>() {}

    @JsonIgnore
    override val httpMethod = HttpMethod.GET
}
