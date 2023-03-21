package com.blog.serachengine.infrastructure.searchengine.naver.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class NaverBlogSearchResponse(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<Item>
) {
    data class Item(
        val title: String,
        val link: String,
        val description: String,
        val bloggername: String,
        val bloggerlink: String,
        @JsonFormat(pattern = "yyyyMMdd")
        val postdate: LocalDate
    )
}
