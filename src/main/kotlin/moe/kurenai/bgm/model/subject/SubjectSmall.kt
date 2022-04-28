package moe.kurenai.bgm.model.subject


class SubjectSmall {
    var id: Int = 0
    var url: String = ""
    var type: Int = 0
    var name: String = ""
    var nameCn: String = ""
    var summary: String = ""
    var airDate: String = ""
    var airWeekday: Int = 0
    var images: Image? = null
    var eps: Int = 0
    var epsCount: Int = 0
    var rating: Rating? = null
    var collection: SubjectCollection? = null
}
