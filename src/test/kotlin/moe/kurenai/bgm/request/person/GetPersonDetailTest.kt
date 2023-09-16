package moe.kurenai.bgm.request.person

import kotlinx.coroutines.runBlocking
import moe.kurenai.bgm.BgmClientFactory
import org.junit.Test
import kotlin.test.assertEquals

/**
 * @author Kurenai
 * @since 2023/9/17 5:45
 */
class GetPersonDetailTest {

    private val client = BgmClientFactory.getClient().coroutine()

    @Test
    fun testFetch() = runBlocking {
        val id = 36024
        val personDetail = client.send(GetPersonDetail(id))
        assertEquals(id, personDetail.id)
    }
}
