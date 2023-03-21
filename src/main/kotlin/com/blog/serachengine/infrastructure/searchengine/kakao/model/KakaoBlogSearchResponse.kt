package com.blog.serachengine.infrastructure.searchengine.kakao.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class KakaoBlogSearchResponse(
    val meta: Meta,
    val documents: List<Document>
) {
    data class Meta(
        val total_count: Int,
        val pageable_count: Int,
        val is_end: Boolean,
    )

    data class Document(
        val title: String,
        val contents: String,
        val url: String,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        val datetime: LocalDateTime
    )
}