package moe.kurenai.bgm.request

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import kotlinx.serialization.DeserializationStrategy

abstract class Request<T> {

    abstract val path: String
    abstract val responseType: TypeReference<T>
    abstract val httpMethod: HttpMethod

    open val responseDeserializer: DeserializationStrategy<T>? = null

    @JsonIgnore
    var token: String? = null

    @JsonIgnore
    open val requireToken: Boolean = false

    @JsonIgnore
    open val isAuthRequest: Boolean = false

}
