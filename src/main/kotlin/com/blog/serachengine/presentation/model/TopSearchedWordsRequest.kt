package com.blog.serachengine.presentation.model

import com.blog.serachengine.application.exception.TopSearchWordsMaximumDurationExceededException
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import java.time.Duration
import java.time.LocalDateTime

data class TopSearchedWordsRequest(
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val start: LocalDateTime = LocalDateTime.now().minusHours(1),
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val end: LocalDateTime = LocalDateTime.now()
) {
    fun validate() {
        if (Duration.between(start, end).seconds > MAXIMUM_DURATION_SECONDS) {
            throw TopSearchWordsMaximumDurationExceededException()
        }
    }

    companion object {
        // 3일
        private const val MAXIMUM_DURATION_SECONDS = 60 * 60 * 24 * 3
    }
}