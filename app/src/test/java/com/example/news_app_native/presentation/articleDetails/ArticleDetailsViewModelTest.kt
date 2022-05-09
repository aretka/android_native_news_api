package com.example.news_app_native.presentation.articleDetails

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.news_app_native.network.models.Article
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ArticleDetailsViewModelTest : TestCoroutineScope by TestCoroutineScope() {

    private val savedState: SavedStateHandle = mock()

    @Test
    fun init_onSuccessArticleGet_emitNonNullArticle() = runBlockingTest {
        whenever(savedState.get<Article>("article")).thenReturn(nonEmptyArticle())
        val fixture = initFixture()
        val expected = ArticleDetailsState(article = nonEmptyArticle())
        fixture.state.test {
            assertEquals(expected, awaitItem())
        }
    }

    @Test
    fun init_onUnsuccessfulArticleGet_emitEmptyArticle() = runBlockingTest {
        whenever(savedState.get<Article>("article")).thenReturn(null)
        val fixture = initFixture()
        val expected = ArticleDetailsState(article = Article())
        fixture.state.test {
            assertEquals(expected, awaitItem())
        }
    }

    private fun nonEmptyArticle(): Article {
        return Article(author = "author1")
    }

    private fun initFixture() : ArticleDetailsViewModel {
        return ArticleDetailsViewModel(savedState)
    }

}