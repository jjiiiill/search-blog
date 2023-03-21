package com.blog.serachengine.infrastructure.searchengine.kakao

import com.blog.serachengine.application.model.BlogSearchParams
import com.blog.serachengine.application.model.BlogSearchResponse
import com.blog.serachengine.infrastructure.searchengine.SearchEngineService
import com.blog.serachengine.infrastructure.searchengine.exception.SearchEngineBadRequestException
import com.blog.serachengine.infrastructure.searchengine.exception.SearchEngineNotFoundException
import com.blog.serachengine.infrastructure.searchengine.kakao.model.KakaoBlogSearchParams
import com.blog.serachengine.infrastructure.searchengine.kakao.model.KakaoBlogSearchResponse
import com.blog.serachengine.util.MultiValueMapConverter
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.reactive.function.client.bodyToMono

@Service
@EnableConfigurationProperties(KakaoSearchEngineProperties::class)
class KakaoSearchEngineService(
    private val properties: KakaoSearchEngineProperties,
    private val webClient: WebClient
) : SearchEngineService {

    override suspend fun searchBlog(params: BlogSearchParams): BlogSearchResponse {
        val kakaoSearchParams = KakaoBlogSearchParams.from(params)

        val response = webClient.get()
            .uri {
                it.scheme(properties.scheme)
                    .host(properties.host)
                    .path(properties.blogSearchPath)
                    .queryParams(MultiValueMapConverter.convert(kakaoSearchParams))
                    .build()
            }
            .headers {
                it.add("Authorization", "KakaoAK ${properties.restApiKey}")
            }
            .exchangeToMono { response ->
                val statusCode = response.statusCode()
                if (statusCode == HttpStatusCode.valueOf(400)) {
                    throw SearchEngineBadRequestException()
                } else if (statusCode == HttpStatusCode.valueOf(404)) {
                    throw SearchEngineNotFoundException()
                } else if (statusCode.is4xxClientError || statusCode.is5xxServerError) {
                    throw WebClientResponseException(
                        response.statusCode().value(),
                        "WebClient Error",
                        response.headers().asHttpHeaders(),
                        null, null
                    )
                }

                response.bodyToMono<KakaoBlogSearchResponse>()
            }.awaitSingleOrNull()

        return BlogSearchResponse.from(response)
    }
}