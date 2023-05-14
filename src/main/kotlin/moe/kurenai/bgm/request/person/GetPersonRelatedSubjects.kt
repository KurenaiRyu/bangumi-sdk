package moe.kurenai.bgm.request.person

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.builtins.ListSerializer
import moe.kurenai.bgm.model.subject.RelatedSubjects
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

@Serializable
data class GetPersonRelatedSubjects(
    @JsonIgnore
    val personId: Int,
) : Request<List<RelatedSubjects>>() {

    @JsonIgnore
    override val responseDeserializer = ListSerializer(RelatedSubjects.serializer())

    @JsonIgnore
    override val path: String = "v0/persons/$personId/subjects"

    @JsonIgnore
    @Transient
    override val responseType = object : TypeReference<List<RelatedSubjects>>() {}

    @JsonIgnore
    override val httpMethod = HttpMethod.GET
}
