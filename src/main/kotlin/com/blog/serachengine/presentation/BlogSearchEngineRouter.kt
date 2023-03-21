package com.blog.serachengine.presentation

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class BlogSearchEngineRouter(
    private val blogSearchEngineHandler: BlogSearchEngineHandler
) {
    @Bean
    fun coRouteBlog(): RouterFunction<ServerResponse> {
        return coRouter {
            (accept(MediaType.APPLICATION_JSON) and "/blogs").nest {
                /**
                 * 블로그 검색
                 */
                GET("", blogSearchEngineHandler::search)

                /**
                 * 기간 내 인기검색어 검색
                 */
                GET("/top-searched-words", blogSearchEngineHandler::getTopSearchedWords)
            }
        }
    }
}