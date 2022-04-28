package moe.kurenai.bgm

import java.io.File

/**
 * @author Kurenai
 * @since 2022/10/22 21:12
 */

object BgmClientFactory {

    private var client: BgmClient? = null

    fun getClient(): BgmClient {
        if (client == null) {
            createClient()
        }
        return client!!
    }

    @Synchronized
    private fun createClient() {
        if (client == null) {
            val evnFile = File(".evn")
            if (evnFile.exists()) {
                evnFile.readLines().mapNotNull {
                    val index = it.indexOf(':')
                    if (index == -1) null
                    else {
                        it.substring(0, index) to it.substring(index + 1, it.length)
                    }
                }.forEach { (k, v) ->
                    System.setProperty(k, v)
                }
            }
            val appId = System.getProperty("APP_ID")
            val appSecret = System.getProperty("APP_SECRET")
            client = BgmClient(appId, appSecret)
        }
    }

}
