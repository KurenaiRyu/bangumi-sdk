package moe.kurenai.bgm.exception

import com.fasterxml.jackson.databind.JsonNode

class ValidationError(
    var detail: JsonNode? = null,
): BgmException() {

}
