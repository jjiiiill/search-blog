package com.blog.serachengine.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class SearchHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val keyword: String,
    val searchYmdt: LocalDateTime
) {
    companion object {
        fun create(keyword: String, searchYmdt: LocalDateTime) = SearchHistory(
            keyword = keyword,
            searchYmdt = searchYmdt
        )
    }
}