package com.blog.serachengine.application

import com.blog.serachengine.domain.SearchHistory
import com.blog.serachengine.domain.SearchHistoryRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
internal class BlogSearchCommandServiceTest {
    @MockK
    private lateinit var searchHistoryRepository: SearchHistoryRepository

    private lateinit var blogSearchCommandService: BlogSearchCommandService

    @BeforeEach
    fun setUp() {
        blogSearchCommandService = BlogSearchCommandService(
            searchHistoryRepository
        )
    }

    @Test
    fun `검색어 저장`() {
        // given
        val query = "검색어 저장 테스트 "
        val searchYmdt = LocalDateTime.now()

        val searchHistories = listOf(
            SearchHistory.create("검색어", searchYmdt),
            SearchHistory.create("저장", searchYmdt),
            SearchHistory.create("테스트", searchYmdt)
        )

        // when
        every { searchHistoryRepository.saveAll(searchHistories) } returns searchHistories

        // then
        blogSearchCommandService.saveKeywords(query, searchYmdt)
        Assertions.assertThatNoException()
    }
}