package com.blog.serachengine.application

import com.blog.serachengine.domain.SearchHistory
import com.blog.serachengine.domain.SearchHistoryRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BlogSearchCommandService(
    private val searchHistoryRepository: SearchHistoryRepository
) {

    /**
     * 검색 히스토리(로그) 저장 (인기검색어 조회용)
     * 검색어는 띄어쓰기 구분하여 각 row로 DB에 저장한다.
     */
    fun saveKeywords(query: String, searchYmdt: LocalDateTime) {
        val searchHistories = query.split(" ")
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .map { SearchHistory.create(it, searchYmdt) }

        searchHistoryRepository.saveAll(searchHistories)
    }
}