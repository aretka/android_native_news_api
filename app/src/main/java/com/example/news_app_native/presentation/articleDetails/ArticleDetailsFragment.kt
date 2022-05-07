package com.example.news_app_native.presentation.articleDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.news_app_native.R
import com.example.news_app_native.databinding.FragmentArticleDetailsBinding
import com.example.news_app_native.network.models.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)
        val article = ArticleDetailsFragmentArgs.fromBundle(requireArguments()).article
        binding.setUpUI(article)
        binding.setUpClickListeners()

        return binding.root
    }

    private fun FragmentArticleDetailsBinding.setUpUI(article: Article) {
        Glide.with(requireContext())
            .load(article.urlToImage)
            .skipMemoryCache(true)
            .placeholder(R.drawable.ic_downloading)
            .error(R.drawable.ic_broken_image)
            .into(articlePicture)
        author.text = article.author ?: "No author"
        date.text = article.publishedAt.toString()
        title.text = article.title ?: "No title"
        content.text = article.content ?: "No content"
    }

    private fun FragmentArticleDetailsBinding.setUpClickListeners() {
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}