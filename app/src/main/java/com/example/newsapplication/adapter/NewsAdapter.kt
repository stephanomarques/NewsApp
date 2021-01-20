package com.example.newsapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.example.newsapplication.entities.News
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(val mContext: Context, val news: List<News>): RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.news_recylerline, parent, false)
        return NewsHolder(view)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val oneNews = news[position]
        holder.titleItemView.text = oneNews.title
        holder.dateItemView.text = oneNews.publishedAt
        holder.descrItemView.text = oneNews.description
        holder.sourceItemView.text = oneNews.source.name
        Glide.with(mContext).load(oneNews.image).into(holder.imageItemView)

    }

    override fun getItemCount(): Int {
        return news.size
    }

    class NewsHolder(val view: View): RecyclerView.ViewHolder(view){
        val titleItemView: TextView = itemView.findViewById(R.id.news_title)
        val sourceItemView: TextView = itemView.findViewById(R.id.news_source)
        val dateItemView: TextView = itemView.findViewById(R.id.news_date)
        val descrItemView: TextView = itemView.findViewById(R.id.news_description)
        val imageItemView: ImageView = itemView.findViewById(R.id.news_image)
    }
}