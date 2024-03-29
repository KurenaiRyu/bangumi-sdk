package moe.kurenai.bgm.request.subject

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.subject.Subject
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

data class GetSubjectRelations(
    @JsonIgnore
    val subjectId: Int,
): Request<Subject>() {
    @JsonIgnore
    override val path: String = "v0/subjects/$subjectId/subjects"

    @JsonIgnore
    override val responseType = object : TypeReference<Subject>() {}

    @JsonIgnore
    override val httpMethod = HttpMethod.GET
}
