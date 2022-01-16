package moe.kurenai.bgm.request.auth

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.auth.AccessToken
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

class AccessTokenRequest(
    val grantType: String,
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String,
    val code: String? = null,
    val refreshToken: String? = null,
    val state: String? = null,
): Request<AccessToken>() {
    @JsonIgnore
    override val method: String = "access_token"
    @JsonIgnore
    override val responseType = object: TypeReference<AccessToken>(){}
    @JsonIgnore
    override val httpMethod = HttpMethod.POST
    @JsonIgnore
    override val needToken: Boolean = false
    @JsonIgnore
    override val isAuthRequest: Boolean = true
}

object AccessTokenGrantType {
    const val REFRESH_TOKEN = "refresh_token"
    const val AUTHORIZATION_CODE = "authorization_code"
}
