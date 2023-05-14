package moe.kurenai.bgm.exception

import kotlinx.serialization.Serializable

@Serializable
class ValidationError(
    var detail: Map<String, String>? = null,
): BgmException() {

}
