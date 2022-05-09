package com.example.news_app_native.presentation.articleList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.collectAsState
import app.cash.turbine.test
import com.example.news_app_native.TestDispatcherRule
import com.example.news_app_native.network.Repository
import com.example.news_app_native.network.models.Article
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class ArticleListViewModelTest : TestCoroutineScope by TestCoroutineScope() {
    @Rule
    @JvmField
    val rule: RuleChain =
        RuleChain.outerRule(TestDispatcherRule()).around(InstantTaskExecutorRule())

    val repository: Repository = mock()

    @Before
    fun setUp() = runBlockingTest {
//        By default emit non empty list on repository fetch
        whenever(repository.getArticles()).thenReturn(emitOneItemList())
    }

    @Test
    fun init_onSuccessArticlesFetch_emitLoaderFalse() = runBlockingTest {
        val fixture = init_fixture()
        fixture.state.test {
            assertFalse(awaitItem().isLoading)
        }
    }

    @Test
    fun init_onSuccessArticlesFetch_emitNonEmptyList() = runBlockingTest {
        val fixture = init_fixture()
        fixture.state.test {
            assertTrue(awaitItem().articleList.isNotEmpty())
        }
    }

    @Test
    fun onSwipeRefreshSuccess_emitNewList() = runBlockingTest {
        val fixture = init_fixture()
        val expected = emitTwoItemList()
        val job = launch {
            fixture.state.test {
                awaitItem()
                assertEquals(expected, awaitItem().articleList)
            }
        }
        fixture.onSwipeRefresh()
        job.join()
        job.cancel()
    }

    @Test
    fun onSwipeRefreshSuccess_emitSuccessEvent() = runBlockingTest {
        val fixture = init_fixture()
        val expected = ArticleEvents.SuccessRefresh()
        val job = launch {
            fixture.events.test {
                assertEquals(expected, awaitItem())
            }
        }
        fixture.onSwipeRefresh()
        job.join()
        job.cancel()
    }

    private fun emitOneItemList(): List<Article> {
        return listOf(Article())
    }

    private fun emitTwoItemList(): List<Article> {
        return listOf(Article(), Article())
    }

    private fun init_fixture(): ArticleListViewModel {
        return ArticleListViewModel(repository)
    }
}