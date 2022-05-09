package com.example.news_app_native.presentation.articleDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.news_app_native.network.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    savedState: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ArticleDetailsState())
    val state = _state.asStateFlow()

    init {
//        savedState.get<Article> should always return non null value
        val articleFromSafeArgs = savedState.get<Article>("article") ?: Article()
        _state.update { it.copy(article = articleFromSafeArgs) }
    }
}