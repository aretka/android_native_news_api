package com.example.news_app_native.network

import android.util.Log
import com.example.news_app_native.network.models.Article
import com.example.news_app_native.network.models.Articles
import javax.inject.Inject

class Repository @Inject constructor(
    private val newsApi: NewsApi
) {
    suspend fun getArticles() : List<Article> {
        val response = newsApi.getTopHeadlines()
        Log.d(TAG, "getArticles response: ${response.code()}")
        return response.body()?.articles ?: Articles().articles
    }

    companion object {
        private val TAG = "Repository"
    }
}