package com.example.news_app_native.presentation.articleList

sealed class ArticleEvents {
    data class SuccessRefresh(val message: String = "Successful refresh") : ArticleEvents()
    data class ErrorRefresh(val message: String = "Unsuccessful refresh") : ArticleEvents()
}