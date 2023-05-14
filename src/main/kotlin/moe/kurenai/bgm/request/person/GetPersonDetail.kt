package moe.kurenai.bgm.request.person

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import moe.kurenai.bgm.model.person.PersonDetail
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

@Serializable
data class GetPersonDetail(
    @JsonIgnore
    val personId: Int,
) : Request<PersonDetail>() {

    @JsonIgnore
    override val responseDeserializer = PersonDetail.serializer()

    @JsonIgnore
    override val path: String = "v0/persons/$personId"

    @JsonIgnore
    @Transient
    override val responseType = object : TypeReference<PersonDetail>() {}

    @JsonIgnore
    override val httpMethod = HttpMethod.GET
}
