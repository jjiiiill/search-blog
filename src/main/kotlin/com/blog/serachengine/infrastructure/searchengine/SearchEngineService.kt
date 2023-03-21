package com.blog.serachengine.infrastructure.searchengine

import com.blog.serachengine.application.model.BlogSearchParams
import com.blog.serachengine.application.model.BlogSearchResponse

interface SearchEngineService {
    suspend fun searchBlog(params: BlogSearchParams): BlogSearchResponse
}