package com.blog.serachengine.infrastructure.searchengine.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class SearchEngineBadRequestException(
    override var message: String = "Bad Request."
) : RuntimeException(message)