package moe.kurenai.bgm.request.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.user.Me
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

data class GetUser (
    @JsonIgnore
    val username: String,
): Request<Me>() {
    @JsonIgnore
    override val method: String = "user/$username"
    @JsonIgnore
    override val responseType = object: TypeReference<Me>(){}
    @JsonIgnore
    override val httpMethod = HttpMethod.GET
}
