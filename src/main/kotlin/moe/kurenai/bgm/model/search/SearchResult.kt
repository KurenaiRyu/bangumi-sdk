package moe.kurenai.bgm.model.search

import moe.kurenai.bgm.model.item.SubjectSmall

data class SearchResult(
    val results: Int,
    val list: List<SubjectSmall>
){

}
