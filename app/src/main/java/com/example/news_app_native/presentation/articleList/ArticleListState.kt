package com.example.news_app_native.presentation.articleList

import com.example.news_app_native.network.models.Article

data class ArticleListState(
    val articleList: List<Article> = emptyList(),
    val isLoading: Boolean = false,
)