package com.example.newsapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.entities.News


class NewsAdapter(val news: List<News>) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_recylerline, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return news.size
    }

     override fun onBindViewHolder(holder: NewsViewHolder, position: Int){
        val current = news[position]
        holder.image.setImageURI(current.image)
        holder.source.text = current.source
        holder.title.text = current.title
    }
}

class NewsViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    val image:ImageView = itemView.findViewById(R.id.news_image)
    val source:TextView = itemView.findViewById(R.id.news_source)
    val title:TextView = itemView.findViewById(R.id.news_title)
}