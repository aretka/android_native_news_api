package com.example.news_app_native.network

import com.example.news_app_native.network.models.Articles
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") language: String = "us"
    ): Response<Articles>
}