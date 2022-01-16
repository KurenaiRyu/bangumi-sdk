package moe.kurenai.bgm.request.subject

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.subject.RelatedPerson
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

data class GetSubjectPersons(
    @JsonIgnore
    val subjectId: Int,
): Request<List<RelatedPerson>>() {
    @JsonIgnore
    override val method: String = "v0/subjects/$subjectId/persons"
    @JsonIgnore
    override val responseType = object: TypeReference<List<RelatedPerson>>(){}
    @JsonIgnore
    override val httpMethod = HttpMethod.GET
}
