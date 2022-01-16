package moe.kurenai.bgm.model.item

class Subject {

    var id: Int = 0
    var type: Int = 0
    var name: String = ""
    var nameCn: String = ""
    var summary: String = ""
    var nsfw: Boolean = false
    var locked: Boolean = false
    var date: String? = ""
    var platform: String = ""
    var images: Image? = null
    var infobox: List<HashMap<String, Any>>? = null
    var volumes: Int = 0
    var eps: Int = 0
    var totalEpisodes: Int = 0
    var rating: Rating? = null
    var collection: SubjectCollection? = null
    var tags: List<HashMap<String, String>>? = null

    constructor()

    constructor(
        id: Int,
        type: Int,
        name: String,
        nameCn: String,
        summary: String,
        nsfw: Boolean,
        locked: Boolean,
        date: String,
        platform: String,
        images: Image,
        infobox: List<HashMap<String, Any>>?,
        volumes: Int,
        eps: Int,
        totalEpisodes: Int,
        rating: Rating,
        collection: SubjectCollection,
        tags: List<HashMap<String, String>>?
    ) {
        this.id = id
        this.type = type
        this.name = name
        this.nameCn = nameCn
        this.summary = summary
        this.nsfw = nsfw
        this.locked = locked
        this.date = date
        this.platform = platform
        this.images = images
        this.infobox = infobox
        this.volumes = volumes
        this.eps = eps
        this.totalEpisodes = totalEpisodes
        this.rating = rating
        this.collection = collection
        this.tags = tags
    }
}
