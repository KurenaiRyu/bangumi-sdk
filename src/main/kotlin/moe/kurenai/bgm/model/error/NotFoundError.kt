package moe.kurenai.bgm.model.error

import kotlinx.serialization.Serializable

@Serializable
class NotFoundError(
    var title: String? = null,
    var description: String? = null,
    var detail: Map<String, String>? = null,
) : BgmError() {
}
