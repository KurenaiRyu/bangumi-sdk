package moe.kurenai.bgm.exception

import moe.kurenai.bgm.model.error.BgmError

class BgmException(
    val error: BgmError
) : Exception("${error.error} - ${error.errorDescription}") {
    override var cause: Throwable? = null
}
