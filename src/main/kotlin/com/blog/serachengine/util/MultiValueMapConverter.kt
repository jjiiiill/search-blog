package com.blog.serachengine.util

import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

object MultiValueMapConverter {
    fun convert(dto: Any): MultiValueMap<String, String> {
        return try {
            jacksonObjectMapper().convertValue<Map<String, String>>(dto)
                .let { LinkedMultiValueMap<String, String>().apply { setAll(it) } }
        } catch (e: Exception) {
            throw IllegalStateException("Failed to convert dto to multiValueMap.")
        }
    }

    fun <T: Any> parse(map: MultiValueMap<String, String>, clazz: Class<T>) : T {
        return try {
            jacksonObjectMapper().convertValue(map.toSingleValueMap(), clazz)
        } catch (e: Exception) {
            throw IllegalStateException("Failed to parse query parameters.")
        }
    }
}