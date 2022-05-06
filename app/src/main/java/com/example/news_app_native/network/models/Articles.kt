package com.example.news_app_native.network.models

data class Articles(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)