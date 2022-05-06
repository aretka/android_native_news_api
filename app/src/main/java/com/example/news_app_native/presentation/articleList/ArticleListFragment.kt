package com.example.news_app_native.presentation.articleList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.news_app_native.databinding.FragmentArticlesListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ArticleListFragment: Fragment() {

    private val viewModel: ArticleListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArticlesListBinding.inflate(inflater, container, false)
        binding.moveBtn.setOnClickListener {
            viewModel.getArticles()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        launches coroutine onStart and cancels onStop
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                Log.d("ArticleListViewModel", "${it.articleList.size}")
            }
        }
    }
}