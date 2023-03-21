package com.blog.serachengine.infrastructure.searchengine.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class SearchEngineNotFoundException(
    override var message: String = "Requested Search-Engine API is not found."
) : RuntimeException(message)