package moe.kurenai.bgm.request.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.user.Me
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

class GetMe(
    @JsonIgnore
    override val token: String
): Request<Me>() {
    @JsonIgnore
    override val method: String = "v0/me"
    @JsonIgnore
    override val responseType = object: TypeReference<Me>(){}
    @JsonIgnore
    override val httpMethod = HttpMethod.GET
    @JsonIgnore
    override val isAuthRequest: Boolean = false
}
