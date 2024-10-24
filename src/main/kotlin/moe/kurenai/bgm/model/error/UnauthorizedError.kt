package moe.kurenai.bgm.model.error

import kotlinx.serialization.Serializable

@Serializable
class UnauthorizedError(
    var title: String? = null,
    var description: String? = null,
    var detail: Map<String, String>? = null,
) : BgmError() {
}
