package moe.kurenai.bgm.exception

import kotlinx.serialization.Serializable

@Serializable
class NotFoundException(
    var title: String? = null,
    var description: String? = null,
    var detail: Map<String, String>? = null,
): BgmException() {
}
