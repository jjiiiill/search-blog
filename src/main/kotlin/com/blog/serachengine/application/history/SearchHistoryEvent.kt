package com.blog.serachengine.application.history

import org.springframework.context.ApplicationEvent

class SearchHistoryEvent(
    val query: String,
) : ApplicationEvent(query) {
}