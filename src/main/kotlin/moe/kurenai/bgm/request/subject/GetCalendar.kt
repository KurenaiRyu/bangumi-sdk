package moe.kurenai.bgm.request.subject

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.subject.Calendar
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

class GetCalendar: Request<List<Calendar>>() {
    @JsonIgnore
    override val path: String = "calendar"

    @JsonIgnore
    override val responseType = object : TypeReference<List<Calendar>>() {}

    @JsonIgnore
    override val httpMethod = HttpMethod.GET
}
