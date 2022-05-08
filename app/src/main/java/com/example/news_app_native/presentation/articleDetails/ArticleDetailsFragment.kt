package com.example.news_app_native.presentation.articleDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.news_app_native.R
import com.example.news_app_native.databinding.FragmentArticleDetailsBinding
import com.example.news_app_native.network.models.Article
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ArticleDetailsFragment : Fragment() {

    private val viewModel: ArticleDetailsViewModel by viewModels()
    private lateinit var binding: FragmentArticleDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)
        binding.setUpClickListeners()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                binding.setUpUI(it.article)
            }
        }
    }

    private fun FragmentArticleDetailsBinding.setUpUI(article: Article) {
        Glide.with(requireContext())
            .load(article.urlToImage)
            .placeholder(R.drawable.ic_downloading)
            .error(R.drawable.ic_broken_image)
            .into(articlePicture)
        author.text = article.author ?: "No author"
        date.text = article.publishedAt.toString()
        title.text = article.title ?: "No title"
        content.text = article.content ?: "No content"
        linkButton.setOnClickListener {
            setButtonClickListener(article.url)
        }
    }

    private fun setButtonClickListener(url: String?) {
        if (url != null) {
            launchBrowser(url)
        } else {
            showToast()
        }
    }

    private fun launchBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
        startActivity(intent)
    }

    private fun showToast() {
        Toast.makeText(context, INVALID_LINK_MESSAGE, Toast.LENGTH_SHORT).show()
    }

    private fun FragmentArticleDetailsBinding.setUpClickListeners() {
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object {
        private const val INVALID_LINK_MESSAGE = "Link is not provided"
    }
}