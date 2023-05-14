package moe.kurenai.bgm.model.search

import kotlinx.serialization.Serializable
import moe.kurenai.bgm.model.subject.SubjectSmall

@Serializable
data class SearchResult(
    val results: Int,
    val list: List<SubjectSmall>
){

}
