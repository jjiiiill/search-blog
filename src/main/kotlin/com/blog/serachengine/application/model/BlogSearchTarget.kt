package com.blog.serachengine.application.model

enum class BlogSearchTarget(
    val kakaoValue: String,
) {
    TITLE("title"),
    ISBN("isbn"),
    PUBLISHER("publisher"),
    PERSON("person"),
}