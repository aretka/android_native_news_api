package com.example.news_app_native.network.models

import android.os.Parcelable
import android.util.Log
import kotlinx.parcelize.Parcelize
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Parcelize
data class Article(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
) : Parcelable {
    fun withFormattedDate(): Article {
        if (publishedAt != null) {
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val formattedDate = try {
                ZonedDateTime.parse(publishedAt).format(formatter)
            } catch (e: Throwable) {
//                Log.e("withFormattedDate", "Failed to parse date", e)
                publishedAt
            }
            return this.copy(publishedAt = formattedDate)
        }
        return this
    }
}