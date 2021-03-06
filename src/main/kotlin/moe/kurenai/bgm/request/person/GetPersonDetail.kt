package moe.kurenai.bgm.request.person

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.person.PersonDetail
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

data class GetPersonDetail(
    @JsonIgnore
    val personId: Int,
) : Request<PersonDetail>() {

    @JsonIgnore
    override val method: String = "v0/persons/$personId"

    @JsonIgnore
    override val responseType = object : TypeReference<PersonDetail>() {}

    @JsonIgnore
    override val httpMethod = HttpMethod.GET
}
