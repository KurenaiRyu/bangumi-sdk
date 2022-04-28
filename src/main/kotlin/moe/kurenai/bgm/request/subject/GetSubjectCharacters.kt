package moe.kurenai.bgm.request.subject

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.subject.RelatedCharacter
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

data class GetSubjectCharacters(
    @JsonIgnore
    val subjectId: Int,
) : Request<List<RelatedCharacter>>() {
    @JsonIgnore
    override val path: String = "v0/subjects/$subjectId/characters"

    @JsonIgnore
    override val responseType = object : TypeReference<List<RelatedCharacter>>() {}

    @JsonIgnore
    override val httpMethod = HttpMethod.GET
}
