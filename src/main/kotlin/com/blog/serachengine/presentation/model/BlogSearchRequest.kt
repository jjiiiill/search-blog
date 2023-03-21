package com.blog.serachengine.presentation.model

import com.blog.serachengine.application.exception.PageMaximumSizeExceededException
import com.blog.serachengine.application.exception.PageSizeMaximumSizeExceededException
import com.blog.serachengine.application.model.BlogSearchParams
import com.blog.serachengine.application.model.BlogSearchResultSort
import com.blog.serachengine.application.model.BlogSearchTarget

data class BlogSearchRequest(
    val query: String,
    val sort: BlogSearchResultSort = BlogSearchResultSort.ACCURACY,
    val page: Int = 1,
    val size: Int = 10,
    val target: BlogSearchTarget? = null
) {
    fun validate() {
        if (page < 1 || page > PAGE_MAXIMUM_SIZE) {
            throw PageMaximumSizeExceededException()
        }

        if (page < 1 || size > SIZE_MAXIMUM_SIZE) {
            throw PageSizeMaximumSizeExceededException()
        }
    }

    fun toParams() = BlogSearchParams(query, sort, page, size, target)

    companion object {
        private const val PAGE_MAXIMUM_SIZE = 50
        private const val SIZE_MAXIMUM_SIZE = 50
    }
}