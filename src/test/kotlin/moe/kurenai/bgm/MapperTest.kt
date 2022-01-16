package moe.kurenai.bgm

import com.fasterxml.jackson.core.type.TypeReference
import moe.kurenai.bgm.model.item.Subject
import moe.kurenai.bgm.model.search.SearchResult
import moe.kurenai.bgm.request.search.SearchSubject
import moe.kurenai.bgm.request.subject.GetSubject
import moe.kurenai.bgm.request.user.GetMe
import moe.kurenai.bgm.util.DefaultMapper
import moe.kurenai.bgm.util.DefaultMapper.MAPPER
import kotlin.test.Test

class MapperTest {

    @Test
    fun test() {
        println(MAPPER.convertValue(GetSubject(11), object : TypeReference<HashMap<String, String>>() {}))
    }

}
