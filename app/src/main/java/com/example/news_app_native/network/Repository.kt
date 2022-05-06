package com.example.news_app_native.network

import com.example.news_app_native.network.models.Article
import javax.inject.Inject

class Repository @Inject constructor(
    private val newsApi: NewsApi
) {
    fun getArticles() : List<Article> {
        val response = newsApi.getTopHeadlines()
        return response.articles
    }
}