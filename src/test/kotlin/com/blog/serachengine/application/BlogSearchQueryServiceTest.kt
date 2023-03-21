package com.blog.serachengine.application

import com.blog.serachengine.application.model.BlogSearchParams
import com.blog.serachengine.application.model.BlogSearchResponse
import com.blog.serachengine.application.model.BlogSearchResultSort
import com.blog.serachengine.domain.SearchHistory
import com.blog.serachengine.domain.SearchHistoryRepository
import com.blog.serachengine.infrastructure.searchengine.kakao.KakaoSearchEngineService
import com.blog.serachengine.infrastructure.searchengine.naver.NaverSearchEngineService
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.BDDAssertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.web.reactive.function.client.WebClientResponseException
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
internal class BlogSearchQueryServiceTest {
    @MockK
    private lateinit var kakaoSearchEngineService: KakaoSearchEngineService

    @MockK
    private lateinit var naverSearchEngineService: NaverSearchEngineService

    @MockK
    private lateinit var searchHistoryRepository: SearchHistoryRepository

    private lateinit var blogSearchQueryService: BlogSearchQueryService

    @BeforeEach
    fun setUp() {
        blogSearchQueryService = BlogSearchQueryService(
            kakaoSearchEngineService,
            naverSearchEngineService,
            searchHistoryRepository
        )
    }

    @Test
    fun `블로그 검색`() {
        // given
        val params = BlogSearchParams(
            query = "test",
            sort = BlogSearchResultSort.ACCURACY,
            page = 1,
            size = 10,
            target = null
        )
        val documents = listOf(
            BlogSearchResponse.Document(
                title = "test",
                contents = "test",
                url = "http://test.com",
                datetime = LocalDateTime.now()
            )
        )
        val response = BlogSearchResponse(
            meta = BlogSearchResponse.Meta(
                totalCount = documents.size,
                pageableCount = documents.size,
                isEnd = true
            ),
            documents = documents
        )

        // when
        coEvery { kakaoSearchEngineService.searchBlog(params) } returns response

        // then
        val result = runBlocking { blogSearchQueryService.search(params) }
        BDDAssertions.then(result.documents.size).isEqualTo(documents.size)
    }

    @Test
    fun `블로그 검색 - 카카오 실패시 네이버로 요청`() {
        // given
        val params = BlogSearchParams(
            query = "test",
            sort = BlogSearchResultSort.ACCURACY,
            page = 1,
            size = 10,
            target = null
        )
        val documents = listOf(
            BlogSearchResponse.Document(
                title = "test",
                contents = "test",
                url = "http://test.com",
                datetime = LocalDateTime.now()
            )
        )
        val response = BlogSearchResponse(
            meta = BlogSearchResponse.Meta(
                totalCount = documents.size,
                pageableCount = documents.size,
                isEnd = true
            ),
            documents = documents
        )

        // when
        coEvery { kakaoSearchEngineService.searchBlog(params) } throws WebClientResponseException(
            500,
            "",
            null,
            null,
            null
        )
        coEvery { naverSearchEngineService.searchBlog(params) } returns response

        // then
        val result = runBlocking { blogSearchQueryService.search(params) }
        BDDAssertions.then(result.documents.size).isEqualTo(documents.size)
    }

    @Test
    fun `인기검색어 검색`() {
        // given
        val start = LocalDateTime.now().minusDays(1)
        val end = LocalDateTime.now()
        val histories = listOf(
            SearchHistory(1, "test1", end),
            SearchHistory(2, "test2", end),
            SearchHistory(3, "test3", end),
            SearchHistory(4, "test4", end),
            SearchHistory(5, "test5", end),
            SearchHistory(6, "test6", end),
            SearchHistory(7, "test7", end),
            SearchHistory(8, "test8", end),
            SearchHistory(9, "test9", end),
            SearchHistory(10, "test10", end),
            SearchHistory(11, "test11", end),
            SearchHistory(12, "test11", end),
        )

        // when
        every { searchHistoryRepository.findBySearchYmdtBetween(start, end) } returns histories

        // then
        val result = blogSearchQueryService.getTopSearchedWords(start, end)
        BDDAssertions.then(result.topSearchedWords.size).isEqualTo(10)
        BDDAssertions.then(result.topSearchedWords.first().word).isEqualTo("test11")
    }
}