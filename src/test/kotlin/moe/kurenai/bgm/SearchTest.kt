package moe.kurenai.bgm

import com.fasterxml.jackson.databind.JsonNode
import moe.kurenai.bgm.BgmClientFactory.getClient
import moe.kurenai.bgm.request.search.SearchSubject
import moe.kurenai.bgm.request.subject.GetSubject
import moe.kurenai.bgm.util.DefaultMapper.MAPPER
import kotlin.test.Test

class SearchTest {

    private val bgmClient = getClient()

    @Test
    fun test() {
        println(MAPPER.writeValueAsString(bgmClient.send(SearchSubject("刀剑神域").apply {
            maxResults = 5
            start = 1
        })))
    }

    @Test
    fun testCharacter() {
        println(MAPPER.writeValueAsString(bgmClient.send(GetSubject(6).apply {
        })))
    }

    @Test
    fun testInfoBox() {
        val json =
            "[{\"key\":\"简体中文名\",\"value\":\"娜娜莉·兰佩路基\"},{\"key\":\"别名\",\"value\":[{\"v\":\"Nunnally vi Britannia\"},{\"k\":\"第二中文名\",\"v\":\"娜娜莉·V·布里塔尼亚\"},{\"k\":\"日文名\",\"v\":\"ナナリー・ヴィ・ブリタニア\"},{\"k\":\"罗马字\",\"v\":\"Nunnally Lamperouge\"}]},{\"key\":\"性别\",\"value\":\"女\"},{\"key\":\"生日\",\"value\":\"2003-10-25\"},{\"key\":\"血型\",\"value\":\"AB\"},{\"key\":\"身高\",\"value\":\"160cm-164cm\"},{\"key\":\"引用来源\",\"value\":\"anidb.net\"}]"
        val node = MAPPER.readValue(json, JsonNode::class.java)
        val result = node.toList().joinToString("\n") {
            val valueNode = it.findValue("value")
            val value = if (valueNode.isTextual) valueNode.textValue()
            else valueNode.toList().joinToString(" | ") { it.findValue("v").textValue() + it.findValue("k")?.textValue()?.replace("\"", "")?.let { "($it)" } ?: "" }
            "${it.findValue("key").textValue()}: $value"
        }
    }

}
