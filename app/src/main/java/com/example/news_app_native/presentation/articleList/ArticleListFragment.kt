package com.example.news_app_native.presentation.articleList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.news_app_native.databinding.FragmentArticlesListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ArticleListFragment : Fragment() {

    private val viewModel: ArticleListViewModel by viewModels()
    private lateinit var adapter: ArticleListAdapter
    private lateinit var binding: FragmentArticlesListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesListBinding.inflate(inflater, container, false)
        binding.setUpAdapter()
        binding.setUpClickListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        launches coroutine onStart and cancels onStop
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                adapter.submitList(state.articleList)
                binding.updateUI(state)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.events.collect { event ->
                when (event) {
                    is ArticleEvents.SuccessRefresh -> onSuccessRefresh(event.message)
                    is ArticleEvents.ErrorRefresh -> onErrorRefresh(event.message)
                }
            }
        }
    }

    private fun onSuccessRefresh(message: String) {
        showShortToast(message)
        binding.swipeRefresh.isRefreshing = false
    }

    private fun onErrorRefresh(message: String) {
        showShortToast(message)
        binding.swipeRefresh.isRefreshing = false
    }

    private fun showShortToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun FragmentArticlesListBinding.setUpAdapter() {
        adapter = ArticleListAdapter(requireContext())
        articleList.adapter = adapter
    }


    private fun FragmentArticlesListBinding.setUpClickListeners() {
        swipeRefresh.setOnRefreshListener {
            viewModel.onSwipeRefresh()
        }
    }

    private fun FragmentArticlesListBinding.updateUI(state: ArticleListState) {
        if (!state.isLoading) {
            progressCircular.visibility = View.GONE
            articleList.visibility = View.VISIBLE
        } else {
            progressCircular.visibility = View.VISIBLE
            articleList.visibility = View.GONE
        }
    }
}
