package com.blog.serachengine.application.model

import com.blog.serachengine.infrastructure.searchengine.kakao.model.KakaoBlogSearchResponse
import com.blog.serachengine.infrastructure.searchengine.naver.model.NaverBlogSearchResponse
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class BlogSearchResponse(
    val meta: Meta,
    val documents: List<Document>
) {
    data class Meta(
        val totalCount: Int,
        val pageableCount: Int,
        val isEnd: Boolean,
    )

    data class Document(
        val title: String,
        val contents: String,
        val url: String,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        val datetime: LocalDateTime
    )

    companion object {
        fun from(response: KakaoBlogSearchResponse?): BlogSearchResponse {
            return response?.let { response ->
                BlogSearchResponse(
                    meta = Meta(
                        response.meta.total_count,
                        response.meta.pageable_count,
                        response.meta.is_end
                    ),
                    documents = response.documents.map {
                        Document(
                            it.title,
                            it.contents,
                            it.url,
                            it.datetime,
                        )
                    }
                )
            } ?: EMPTY_RESPONSE
        }

        fun from(response: NaverBlogSearchResponse?): BlogSearchResponse {
            return response?.let { response ->
                BlogSearchResponse(
                    meta = Meta(
                        response.total,
                        response.display,
                        response.start + response.display == response.total
                    ),
                    documents = response.items.map {
                        Document(
                            it.title,
                            it.description,
                            it.link,
                            it.postdate.atStartOfDay(),
                        )
                    }
                )
            } ?: EMPTY_RESPONSE
        }

        private val EMPTY_RESPONSE = BlogSearchResponse(
            meta = Meta(0, 0, true),
            documents = emptyList()
        )
    }
}