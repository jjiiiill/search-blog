package com.blog.serachengine.infrastructure.searchengine.naver

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "search-engine.naver")
data class NaverSearchEngineProperties(
    val scheme: String,
    val host: String,
    val blogSearchPath: String,
    val naverClientId: String,
    val naverClientSecret: String,
)