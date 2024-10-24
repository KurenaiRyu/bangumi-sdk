package moe.kurenai.bgm.model.error

import kotlinx.serialization.Serializable

@Serializable
class ValidationError(
    var detail: Map<String, String>? = null,
) : BgmError() {

}
