package moe.kurenai.bgm.request

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference

abstract class Request<T> {

    abstract val method: String
    abstract val responseType: TypeReference<T>
    abstract val httpMethod: HttpMethod
    @JsonIgnore
    open val token: String? = null
    @JsonIgnore
    open val needToken: Boolean = false
    @JsonIgnore
    open val isAuthRequest: Boolean = false

}
