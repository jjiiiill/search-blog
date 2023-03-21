package com.blog.serachengine.application.model

data class TopSearchedWordsResponse(
    val topSearchedWords: List<SearchedWord>
) {
    data class SearchedWord(
        val word: String,
        val count: Long,
    )
}