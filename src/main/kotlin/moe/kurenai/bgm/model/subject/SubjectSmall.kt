package moe.kurenai.bgm.model.subject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SubjectSmall {
    var id: Int = 0
    var url: String = ""
    var type: Int = 0
    var name: String = ""

    @SerialName("name_cn")
    var nameCn: String = ""
    var summary: String = ""

    @SerialName("air_date")
    var airDate: String = ""

    @SerialName("air_weekday")
    var airWeekday: Int = 0
    var images: Image? = null
    var eps: Int = 0

    @SerialName("eps_count")
    var epsCount: Int = 0
    var rating: Rating? = null
    var collection: SubjectCollection? = null
}
