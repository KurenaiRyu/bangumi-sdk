package moe.kurenai.bgm.request.person

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.builtins.ListSerializer
import moe.kurenai.bgm.model.character.CharacterPerson
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

@Serializable
data class GetPersonRelatedCharacters(
    @JsonIgnore
    val personId: Int,
) : Request<List<CharacterPerson>>() {

    @JsonIgnore
    override val responseDeserializer = ListSerializer(CharacterPerson.serializer())

    @JsonIgnore
    override val path: String = "v0/persons/$personId/characters"

    @JsonIgnore
    @Transient
    override val responseType = object : TypeReference<List<CharacterPerson>>() {}

    @JsonIgnore
    override val httpMethod = HttpMethod.GET
}
