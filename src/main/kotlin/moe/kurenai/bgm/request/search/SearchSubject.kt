package moe.kurenai.bgm.request.search

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.search.SearchResult
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class SearchSubject(
    @JsonIgnore
    val keywords: String,
    var type: Int? = null,
    var responseGroup: String? = null,
    var start: Int? = null,
    var maxResults: Int? = null,
): Request<SearchResult>() {
    @JsonIgnore
    override val path: String = "search/subject/${URLEncoder.encode(keywords, StandardCharsets.UTF_8)}"

    @JsonIgnore
    override val responseType = object : TypeReference<SearchResult>() {}

    @JsonIgnore
    override val httpMethod = HttpMethod.GET

    @JsonIgnore
    override val requireToken: Boolean = true

    @JsonIgnore
    override val isAuthRequest: Boolean = false

}

object ResponseGroup {
    const val SMALL = "small"
    const val MEDIUM = "medium"
}
