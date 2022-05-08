package com.example.news_app_native.presentation.articleList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app_native.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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
            val articles = repository.getArticles()
            _state.update { it.copy(articleList = articles) }
            _events.emit(ArticleEvents.SuccessRefresh())
        }
    }

    private fun getArticles() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val articles = repository.getArticles()
            _events.emit(ArticleEvents.SuccessRefresh())
            _state.update { it.copy(articleList = articles, isLoading = false) }
        }
    }

}