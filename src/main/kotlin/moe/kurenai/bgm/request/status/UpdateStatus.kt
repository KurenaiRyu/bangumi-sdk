package moe.kurenai.bgm.request.status

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.Response
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

data class UpdateStatus(
    @JsonIgnore
    val id: Int,
    @JsonIgnore
    val status: Int,
    val epId: String,
): Request<Response>() {
    @JsonIgnore
    override val path: String = "/ep/$id/status/$status"

    @JsonIgnore
    override val responseType = object : TypeReference<Response>() {}

    @JsonIgnore
    override val httpMethod = HttpMethod.GET
}
