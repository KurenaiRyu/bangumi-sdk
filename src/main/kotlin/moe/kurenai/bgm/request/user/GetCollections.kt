package moe.kurenai.bgm.request.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import moe.kurenai.bgm.model.Page
import moe.kurenai.bgm.model.user.UserCollection
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

@Serializable
class GetCollections (
    @JsonIgnore
    val username: String,
): Request<Page<UserCollection>>() {

    companion object {
        val jacksonType = jacksonTypeRef<Page<UserCollection>>()
    }

    var subjectType: Int? = null
    var type: Int? = null
    var limit: Int? = null
    var offset: Int? = null

    @JsonIgnore
    override val path: String = "v0/users/$username/collections"

    @JsonIgnore
    @Transient
    override val responseType = jacksonType

    @JsonIgnore
    override val responseDeserializer = Page.serializer(UserCollection.serializer())

    @JsonIgnore
    override val httpMethod = HttpMethod.GET

    @JsonIgnore
    override val isAuthRequest: Boolean = false
}
