package com.blog.serachengine.application.history

import com.blog.serachengine.application.BlogSearchCommandService
import org.springframework.context.ApplicationListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Component
class SearchHistoryEventListener(
    private val blogSearchCommandService: BlogSearchCommandService
) : ApplicationListener<SearchHistoryEvent> {
    @Async
    override fun onApplicationEvent(event: SearchHistoryEvent) {
        blogSearchCommandService.saveKeywords(
            event.query,
            LocalDateTime.ofInstant(
                Instant.ofEpochMilli(event.timestamp),
                TimeZone.getTimeZone("Asia/Seoul").toZoneId()
            )
        )
    }
}