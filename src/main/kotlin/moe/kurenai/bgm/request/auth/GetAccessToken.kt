package moe.kurenai.bgm.request.auth

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import moe.kurenai.bgm.model.auth.AccessToken
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

@Serializable
class AccessTokenRequest(
    @SerialName("grant_type")
    val grantType: String,
    @SerialName("client_id")
    val clientId: String,
    @SerialName("client_secret")
    val clientSecret: String,
    @SerialName("redirect_uri")
    val redirectUri: String,
    val code: String? = null,
    @SerialName("refresh_token")
    val refreshToken: String? = null,
    val state: String? = null,
): Request<AccessToken>() {

    override val responseDeserializer: DeserializationStrategy<AccessToken> = AccessToken.serializer()

    @JsonIgnore
    override val path: String = "access_token"

    @JsonIgnore
    @Transient
    override val responseType = object : TypeReference<AccessToken>() {}

    @JsonIgnore
    override val httpMethod = HttpMethod.POST

    @JsonIgnore
    override val isAuthRequest: Boolean = true
}

object AccessTokenGrantType {
    const val REFRESH_TOKEN = "refresh_token"
    const val AUTHORIZATION_CODE = "authorization_code"
}
