package moe.kurenai.bgm.model.search

import moe.kurenai.bgm.model.subject.SubjectSmall

data class SearchResult(
    val results: Int,
    val list: List<SubjectSmall>
){

}
