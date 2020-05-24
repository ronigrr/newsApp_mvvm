package com.example.newsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data_models.ArticleModel
import kotlinx.android.synthetic.main.news_item.view.*

class NewsListAdapter (private var newsList : List<ArticleModel> = ArrayList()) : RecyclerView.Adapter<NewsListAdapter.ViewHolder> () {

    class ViewHolder (private val view : View, private val context: Context) : RecyclerView.ViewHolder(view){
        fun bind (newsItem : ArticleModel){
            view.tvCardHeader.text = newsItem.title;
            view.tvCardDescription.text = newsItem.description
            Glide.with(context).load(newsItem.urlToImage).thumbnail(0.1f).into(view.ivCardimage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        return ViewHolder(view,parent.context)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article : ArticleModel = newsList[position]
        holder.bind(article)
    }
}