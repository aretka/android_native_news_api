package com.example.news_app_native.network.models

data class Articles(
    val articles: List<Article> = emptyList(),
    val status: String = "",
    val totalResults: Int? = null
)