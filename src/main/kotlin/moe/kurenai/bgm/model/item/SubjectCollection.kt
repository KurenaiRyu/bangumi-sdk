package moe.kurenai.bgm.model.item

class SubjectCollection{
    var wish: Int = 0
    var collect: Int = 0
    var doing: Int = 0
    var onHold: Int = 0
    var dropped: Int = 0

    constructor()

    constructor(wish: Int, collect: Int, doing: Int, onHold: Int, dropped: Int) {
        this.wish = wish
        this.collect = collect
        this.doing = doing
        this.onHold = onHold
        this.dropped = dropped
    }
}
