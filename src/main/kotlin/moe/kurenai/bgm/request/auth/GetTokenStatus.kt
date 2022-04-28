package moe.kurenai.bgm.request.auth

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.auth.TokenStatus
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

data class GetTokenStatus(
    val accessToken: String
): Request<TokenStatus>() {
    @JsonIgnore
    override val path: String = "access_token"

    @JsonIgnore
    override val responseType = object : TypeReference<TokenStatus>() {}

    @JsonIgnore
    override val httpMethod = HttpMethod.POST

    @JsonIgnore
    override val requireToken: Boolean = false

    @JsonIgnore
    override val isAuthRequest: Boolean = true
}
