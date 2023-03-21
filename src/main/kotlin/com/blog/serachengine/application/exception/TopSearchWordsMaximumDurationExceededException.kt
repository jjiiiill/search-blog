package com.blog.serachengine.application.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class TopSearchWordsMaximumDurationExceededException(
    override var message: String = "Top Search Words request cannot exceeded 3days."
) : RuntimeException(message)