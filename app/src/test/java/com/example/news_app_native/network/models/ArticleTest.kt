package com.example.news_app_native.network.models

import org.junit.Test
import kotlin.test.assertEquals

class ArticleTest {

    @Test
    fun onCorrectDate_emitFormattedDate() {
        val actual = articleWithCorrectDate().withFormattedDate().publishedAt
        val expected = "08-05-2022"
        print(actual)
        assertEquals(expected, actual)
    }

    @Test
    fun onInCorrectDate_emitSameDate() {
        val actual = articleWithIncorrectDate().withFormattedDate().publishedAt
        print(actual)
        val expected = articleWithIncorrectDate().publishedAt
        assertEquals(expected, actual)
    }

    @Test
    fun onNullDate_emitSameDate() {
        val actual = Article().withFormattedDate().publishedAt
        val expected = Article().publishedAt
        assertEquals(expected, actual)
    }

    private fun articleWithCorrectDate() = Article(publishedAt = "2022-05-08T21:49:16Z")
    private fun articleWithIncorrectDate() = Article(publishedAt = "2022-05-0821:aa")

}