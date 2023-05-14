package moe.kurenai.bgm.request.subject

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.builtins.ListSerializer
import moe.kurenai.bgm.model.subject.Calendar
import moe.kurenai.bgm.request.HttpMethod
import moe.kurenai.bgm.request.Request

@Serializable
class GetCalendar: Request<List<Calendar>>() {

    @JsonIgnore
    override val responseDeserializer = ListSerializer(Calendar.serializer())

    @JsonIgnore
    override val path: String = "calendar"

    @JsonIgnore
    @Transient
    override val responseType = object : TypeReference<List<Calendar>>() {}

    @JsonIgnore
    override val httpMethod = HttpMethod.GET
}
