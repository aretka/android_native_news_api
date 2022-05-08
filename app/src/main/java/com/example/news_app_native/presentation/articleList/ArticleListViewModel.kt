package com.example.news_app_native.presentation.articleList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app_native.network.Repository
import com.example.news_app_native.network.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(ArticleListState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<ArticleEvents>()
    val events = _events.asSharedFlow()

    init {
        getArticles()
    }

    fun onSwipeRefreshClick() {
        viewModelScope.launch {
            val articles = tryToGetArticles()
            _state.update { it.copy(articleList = articles) }
            _events.emit(ArticleEvents.SuccessRefresh())
        }
    }

    private fun getArticles() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val articles = tryToGetArticles()
            _state.update { it.copy(articleList = articles, isLoading = false) }
        }
    }

    private suspend fun tryToGetArticles() : List<Article>{
        return try {
            repository.getArticles()
        } catch (e: Exception) {
            Log.e("ArticleListViewModel", "Failed to fetch articles", e)
            emptyList()
        }
    }

}