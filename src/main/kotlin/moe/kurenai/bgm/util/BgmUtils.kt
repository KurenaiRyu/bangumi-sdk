package moe.kurenai.bgm.util

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object BgmUtils {

    fun String.urlEncode(): String {
        return URLEncoder.encode(this, StandardCharsets.UTF_8)
    }

}
