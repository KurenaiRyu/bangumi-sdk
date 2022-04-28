package moe.kurenai.bgm.model.subject

import com.fasterxml.jackson.databind.JsonNode

class Subject {

    var id: Int = 0
    var type: Int = 0
    var name: String = ""
    var nameCn: String = ""
    var summary: String = ""
    var nsfw: Boolean = false
    var locked: Boolean = false
    var date: String? = ""
    var platform: String? = null
    var images: Image? = null
    var infobox: JsonNode? = null
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
        images: Image?,
        infobox: JsonNode?,
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
