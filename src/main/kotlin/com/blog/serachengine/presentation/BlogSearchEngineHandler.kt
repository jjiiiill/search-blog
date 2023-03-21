package com.blog.serachengine.presentation

import com.blog.serachengine.application.BlogSearchQueryService
import com.blog.serachengine.application.history.SearchHistoryEvent
import com.blog.serachengine.presentation.model.BlogSearchRequest
import com.blog.serachengine.presentation.model.TopSearchedWordsRequest
import com.blog.serachengine.util.MultiValueMapConverter
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class BlogSearchEngineHandler(
    private val blogSearchQueryService: BlogSearchQueryService,
    private val eventPublisher: ApplicationEventPublisher,
) {

    /**
     * 블로그 검색
     */
    suspend fun search(request: ServerRequest): ServerResponse {
        val searchRequest =
            MultiValueMapConverter.parse(request.queryParams(), BlogSearchRequest::class.java)
                .also {
                    it.validate()
                }
        val response = blogSearchQueryService.search(searchRequest.toParams())

        /**
         * 인기검색어 조회용 검색 log(history) 저장
         * Spring Event 발행을 통한 비동기 저장
         */
        eventPublisher.publishEvent(SearchHistoryEvent(searchRequest.query))

        return ServerResponse.ok().bodyValueAndAwait(response)
    }

    /**
     * 기간 내 인기검색어 검색
     */
    suspend fun getTopSearchedWords(request: ServerRequest): ServerResponse {
        val searchRequest =
            MultiValueMapConverter.parse(request.queryParams(), TopSearchedWordsRequest::class.java)
                .also {
                    it.validate()
                }

        return ServerResponse.ok().bodyValueAndAwait(
            blogSearchQueryService.getTopSearchedWords(
                searchRequest.start,
                searchRequest.end
            )
        )
    }
}