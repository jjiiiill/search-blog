package com.blog.serachengine.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface SearchHistoryRepository : JpaRepository<SearchHistory, Long> {
    fun findBySearchYmdtBetween(start: LocalDateTime, end: LocalDateTime): List<SearchHistory>
}