package moe.kurenai.bgm.model.item

class Image{
    var large: String = ""
    var common: String = ""
    var medium: String = ""
    var small: String = ""
    var grid: String = ""

    constructor()

    constructor(large: String, common: String, medium: String, small: String, grid: String) {
        this.large = large
        this.common = common
        this.medium = medium
        this.small = small
        this.grid = grid
    }
}
