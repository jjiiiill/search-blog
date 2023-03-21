package com.blog.serachengine.application.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class PageSizeMaximumSizeExceededException(
    override var message: String = "Page size must be 1 ~ 50."
) : RuntimeException(message)