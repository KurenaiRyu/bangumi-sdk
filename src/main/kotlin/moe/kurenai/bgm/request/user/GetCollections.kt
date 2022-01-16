package moe.kurenai.bgm.request.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.Page
import moe.kurenai.bgm.model.user.Me
import moe.kurenai.bgm.model.user.UserCollection
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

class GetCollections (
    @JsonIgnore
    val username: String,
): Request<Page<UserCollection>>() {
    @JsonIgnore
    override var token: String? = null
    var subjectType: Int? = null
    var type: Int? = null
    var limit: Int? = null
    var offset: Int? = null
    @JsonIgnore
    override val method: String = "v0/users/$username/collections"
    @JsonIgnore
    override val responseType = object: TypeReference<Page<UserCollection>>(){}
    @JsonIgnore
    override val httpMethod = HttpMethod.GET
    @JsonIgnore
    override val isAuthRequest: Boolean = false
}
