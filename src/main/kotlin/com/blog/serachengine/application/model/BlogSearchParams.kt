package com.blog.serachengine.application.model

data class BlogSearchParams(
    val query: String,
    val sort: BlogSearchResultSort,
    val page: Int,
    val size: Int,
)