package com.example.news_app_native.presentation.articleList

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.news_app_native.R
import com.example.news_app_native.databinding.ListItemArticleBinding
import com.example.news_app_native.network.models.Article

class ArticleListAdapter(private val context: Context) :
    ListAdapter<Article, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return ArticleViewHolder.from(parent, context)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val articleHolder = holder as ArticleViewHolder
        val item = currentList[position]
        articleHolder.bind(item)
    }

    class ArticleViewHolder(private val binding: ListItemArticleBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.title.text = item.title?: "No title"
            binding.description.text = item.description?: "No description"
            Log.d("OnBind", "glideUrl: ${item.urlToImage}")
            Glide.with(context)
                .load(item.urlToImage)
                .skipMemoryCache(true)
                .placeholder(R.drawable.ic_downloading)
                .error(R.drawable.ic_broken_image)
                .into(binding.picture)
            binding.picture.clipToOutline = true
            binding.root.setOnClickListener { view ->
                val direction = ArticleListFragmentDirections
                    .actionFragmentArticleListToFragmentArticleDetails(item)
                view.findNavController().navigate(direction)
            }
        }

        companion object {
            fun from(parent: ViewGroup, context: Context): ArticleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ListItemArticleBinding.inflate(layoutInflater, parent, false)
                return ArticleViewHolder(view, context)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Article>() {
        //        We will only keep items the same if their all contents are the same
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
}