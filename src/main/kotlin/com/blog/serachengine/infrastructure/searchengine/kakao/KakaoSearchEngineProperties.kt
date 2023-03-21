package com.blog.serachengine.infrastructure.searchengine.kakao

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "search-engine.kakao")
data class KakaoSearchEngineProperties(
    val scheme: String,
    val host: String,
    val blogSearchPath: String,
    val restApiKey: String
)