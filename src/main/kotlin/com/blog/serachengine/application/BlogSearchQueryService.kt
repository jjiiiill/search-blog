package com.blog.serachengine.application

import com.blog.serachengine.application.model.BlogSearchParams
import com.blog.serachengine.application.model.BlogSearchResponse
import com.blog.serachengine.application.model.TopSearchedWordsResponse
import com.blog.serachengine.domain.SearchHistoryRepository
import com.blog.serachengine.infrastructure.searchengine.kakao.KakaoSearchEngineService
import com.blog.serachengine.infrastructure.searchengine.naver.NaverSearchEngineService
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClientResponseException
import java.time.LocalDateTime

@Service
class BlogSearchQueryService(
    private val kakaoSearchEngineService: KakaoSearchEngineService,
    private val naverSearchEngineService: NaverSearchEngineService,
    private val searchHistoryRepository: SearchHistoryRepository
) {
    suspend fun search(params: BlogSearchParams): BlogSearchResponse {
        return try {
            kakaoSearchEngineService.searchBlog(params)
        } catch (e: WebClientResponseException) {
            if (e.statusCode.is5xxServerError) {
                naverSearchEngineService.searchBlog(params)
            } else {
                throw e
            }
        }
    }

    fun getTopSearchedWords(start: LocalDateTime, end: LocalDateTime): TopSearchedWordsResponse {
        val histories = searchHistoryRepository.findBySearchYmdtBetween(start, end)
        // keyword 별 history list 생성
        val searchedWordsList = histories.groupBy { it.keyword }
            .toList()
        // 인기검색어 목록은 최대 10개만 표시
        val maximumSize = if (searchedWordsList.size > 10) 10 else searchedWordsList.size

        // 많이 검색된 순으로 정렬 후 10개 이하로 목록을 subList하여 응답 생성
        val subList = searchedWordsList.sortedByDescending { it.second.size }
            .subList(0, maximumSize)
            .toMap()
            .map {
                TopSearchedWordsResponse.SearchedWord(
                    it.key, it.value.size.toLong()
                )
            }

        return TopSearchedWordsResponse(subList)
    }
}