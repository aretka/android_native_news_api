package com.example.news_app_native.presentation.articleList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app_native.databinding.ListItemArticleBinding
import com.example.news_app_native.network.models.Article

class ArticleListAdapter :
    ListAdapter<Article, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return ArticleViewHolder.from(parent)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val articleHolder = holder as ArticleViewHolder
        val item = currentList[position]
        articleHolder.bind(item)
    }

    class ArticleViewHolder(private val binding: ListItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.title.text = item.title
            binding.description.text = item.description
        }

        companion object {
            fun from(parent: ViewGroup): ArticleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ListItemArticleBinding.inflate(layoutInflater, parent, false)
                return ArticleViewHolder(view)
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