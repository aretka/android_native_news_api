package com.example.news_app_native.presentation.articleDetails

import com.example.news_app_native.network.models.Article

data class ArticleDetailsState(
    val article: Article = Article()
)