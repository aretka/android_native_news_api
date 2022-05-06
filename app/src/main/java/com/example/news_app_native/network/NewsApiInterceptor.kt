package com.example.news_app_native.network

import okhttp3.Interceptor
import okhttp3.Response

class NewsApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
//        API key should be hidden
        val newRequest = chain.request().newBuilder()
            .addHeader("X-Api-Key", "53dbe05b0b9a4154a8f5b44b2e9201ec")
            .build()

        return chain.proceed(newRequest)
    }
}