package com.blog.serachengine.infrastructure.searchengine.kakao.model

import com.blog.serachengine.application.model.BlogSearchParams

data class KakaoBlogSearchParams(
    val query: String,
    val sort: String,
    val page: Int,
    val size: Int,
) {
    companion object {
        fun from(params: BlogSearchParams) = KakaoBlogSearchParams(
            query = params.query,
            sort = params.sort.kakaoValue,
            page = params.page,
            size = params.size,
        )
    }
}