package com.example.news_app_native.network

import com.example.news_app_native.network.models.Article
import javax.inject.Inject

class Repository @Inject constructor(
    private val newsApi: NewsApi
) {
    suspend fun getArticles(): List<Article> {
        val response = newsApi.getTopHeadlines()
        var articles: List<Article> = emptyList()
        if (response.body() != null) {
            articles = response.body()!!.articles.map {
                it.withFormattedDate()
            }
        }
        return articles
    }

    companion object {
        private val TAG = "Repository"
    }
}