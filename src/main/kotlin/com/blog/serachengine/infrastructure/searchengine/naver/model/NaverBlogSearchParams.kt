package com.blog.serachengine.infrastructure.searchengine.naver.model

import com.blog.serachengine.application.model.BlogSearchParams

data class NaverBlogSearchParams(
    val query: String,
    val display: Int,
    val start: Int,
    val sort: String,
) {
    companion object {
        fun from(params: BlogSearchParams) = NaverBlogSearchParams(
            query = params.query,
            display = params.size,
            start = ((params.page - 1) * params.size) + 1,
            sort = params.sort.naverValue
        )
    }
}