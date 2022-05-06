package com.example.news_app_native.network

import androidx.lifecycle.LiveData
import com.example.news_app_native.network.models.Articles
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    fun getTopHeadlines(
        @Query("country") language: String = "us"
    ): Articles
}