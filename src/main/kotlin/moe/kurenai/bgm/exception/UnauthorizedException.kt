package moe.kurenai.bgm.exception

import com.fasterxml.jackson.databind.JsonNode

class UnauthorizedException(
    var title: String? = null,
    var description: String? = null,
    var detail: JsonNode? = null,
) : BgmException() {
}
