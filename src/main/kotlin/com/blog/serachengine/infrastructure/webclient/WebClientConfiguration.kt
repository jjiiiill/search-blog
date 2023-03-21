package com.blog.serachengine.infrastructure.webclient

import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.InsecureTrustManagerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient

@Configuration
class WebClientConfiguration {

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .codecs { it.defaultCodecs().maxInMemorySize(2 * 1024 * 1024) }
            .clientConnector(
                ReactorClientHttpConnector(
                    HttpClient.create()
                        .secure {
                            it.sslContext(
                                SslContextBuilder.forClient()
                                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build()
                            )
                        }
                )
            )
            .build()
    }
}