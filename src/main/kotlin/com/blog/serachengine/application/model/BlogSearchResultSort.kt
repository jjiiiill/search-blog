package com.blog.serachengine.application.model

enum class BlogSearchResultSort(
    val kakaoValue: String,
    val naverValue: String,
) {
    ACCURACY("accuracy", "sim"),
    RECENCY("recency", "date")
}