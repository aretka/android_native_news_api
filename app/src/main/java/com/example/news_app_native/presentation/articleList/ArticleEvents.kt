package com.example.news_app_native.presentation.articleList

sealed class ArticleEvents {
    data class SuccessFetch(val message: String = "Successful data fetch") : ArticleEvents ()
    data class ErrorFetch(val message: String = "Unsuccessful data fetch") : ArticleEvents()
    data class SuccessRefresh(val message: String = "Successful refresh") : ArticleEvents()
    data class ErrorRefresh(val message: String = "Unsuccessful refresh") : ArticleEvents()
}