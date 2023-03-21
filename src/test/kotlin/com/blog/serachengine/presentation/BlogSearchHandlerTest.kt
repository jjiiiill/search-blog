package com.blog.serachengine.presentation

import com.blog.serachengine.application.BlogSearchQueryService
import com.blog.serachengine.application.exception.PageMaximumSizeExceededException
import com.blog.serachengine.application.exception.PageSizeMaximumSizeExceededException
import com.blog.serachengine.presentation.model.BlogSearchRequest
import com.blog.serachengine.util.MultiValueMapConverter
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.ApplicationEventPublisher
import org.springframework.mock.web.reactive.function.server.MockServerRequest

@ExtendWith(MockKExtension::class)
internal class BlogSearchHandlerTest {
    @MockK
    private lateinit var blogSearchQueryService: BlogSearchQueryService

    @MockK
    private lateinit var eventPublisher: ApplicationEventPublisher

    private lateinit var blogSearchEngineHandler: BlogSearchEngineHandler

    @BeforeEach
    fun setUp() {
        blogSearchEngineHandler = BlogSearchEngineHandler(
            blogSearchQueryService,
            eventPublisher
        )
    }

    @Test
    fun `블로그 검색 - 검색어 저장`() {
        // given
        val query = "검색어 저장 테스트"
        val blogSearchRequest = BlogSearchRequest(
            query = query,
        )
        val request = MockServerRequest.builder().queryParams(
            MultiValueMapConverter.convert(blogSearchRequest)
        ).build()

        // when
        coEvery { blogSearchQueryService.search(blogSearchRequest.toParams()) } returns mockk()
        coEvery { eventPublisher.publishEvent(any()) } just runs

        // then
        runBlocking { blogSearchEngineHandler.search(request) }
        verify { eventPublisher.publishEvent(any()) }
    }

    @Test
    fun `블로그 검색 - 잘못된 page`() {
        // given
        val query = "test"
        val invalidPage = 100
        val blogSearchRequest = BlogSearchRequest(
            query = query,
            page = invalidPage
        )
        val request = MockServerRequest.builder().queryParams(
            MultiValueMapConverter.convert(blogSearchRequest)
        ).build()

        // when

        // then
        val exception = assertThrows<PageMaximumSizeExceededException> {
            runBlocking { blogSearchEngineHandler.search(request) }
        }
        Assertions.assertEquals(exception.message, "Page must be 1 ~ 50.")
    }

    @Test
    fun `블로그 검색 - 잘못된 page size`() {
        // given
        val query = "test"
        val invalidPageSize = 100
        val blogSearchRequest = BlogSearchRequest(
            query = query,
            size = invalidPageSize
        )
        val request = MockServerRequest.builder().queryParams(
            MultiValueMapConverter.convert(blogSearchRequest)
        ).build()

        // when

        // then
        val exception = assertThrows<PageSizeMaximumSizeExceededException> {
            runBlocking { blogSearchEngineHandler.search(request) }
        }
        Assertions.assertEquals(exception.message, "Page size must be 1 ~ 50.")
    }
}