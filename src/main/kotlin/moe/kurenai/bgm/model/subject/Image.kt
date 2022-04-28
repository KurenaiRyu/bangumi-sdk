package moe.kurenai.bgm.model.subject

import moe.kurenai.bgm.model.subject.Image.Companion.DEFAULT_IMAGE

class Image {

    companion object {
        const val DEFAULT_IMAGE = "https://bgm.tv/img/no_icon_subject.png"
    }

    var large: String? = null
    var medium: String? = null
    var common: String? = null
    var grid: String? = null
    var small: String? = null

}

fun Image?.getLarge(): String {
    return this?.large?.takeIf { it.isNotBlank() } ?: this.getMedium()
}

fun Image?.getMedium(): String {
    return this?.medium?.takeIf { it.isNotBlank() } ?: getCommon()
}

fun Image?.getCommon(): String {
    return this?.common?.takeIf { it.isNotBlank() } ?: getGrid()
}

fun Image?.getGrid(): String {
    return this?.grid?.takeIf { it.isNotBlank() } ?: getSmall()
}

fun Image?.getSmall(): String {
    return this?.small?.takeIf { it.isNotBlank() } ?: DEFAULT_IMAGE
}
