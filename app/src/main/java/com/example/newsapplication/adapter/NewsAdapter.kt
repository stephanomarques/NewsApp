package com.example.newsapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.newsapplication.HomePageActivity
import com.example.newsapplication.R
import com.example.newsapplication.entities.News
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(
        val mContext: Context,
        val news: List<News>,
        val listener: ClickListener): RecyclerView.Adapter<NewsAdapter.NewsHolder>() {



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

        holder.itemView.setOnClickListener{
            listener.ClickedItem(oneNews)
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class NewsHolder(private val thisItemView: View): RecyclerView.ViewHolder(thisItemView){

        val titleItemView: TextView = thisItemView.findViewById(R.id.news_title)
        val sourceItemView: TextView = thisItemView.findViewById(R.id.news_source)
        val dateItemView: TextView = thisItemView.findViewById(R.id.news_date)
        val descrItemView: TextView = thisItemView.findViewById(R.id.news_description)
        val imageItemView: ImageView = thisItemView.findViewById(R.id.news_image)


        /*init{
            thisItemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.ClickedItem(current)
        }*/


    }

    //Click Listener for Delete Button
    interface ClickListener {
        fun ClickedItem(news: News)
    }



}