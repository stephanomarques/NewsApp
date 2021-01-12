package com.example.newsapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.entities.News
import ipvc.estg.trabparte3.api.Marker


class MarkerAdapter(val news: List<News>) : RecyclerView.Adapter<MarkersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_recylerline, parent, false)
        return MarkersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return markers.size
    }

     override fun onBindViewHolder(holder: MarkersViewHolder, position: Int){
        val current = markers[position]
        holder.title.text = current.title
        holder.type.text = current.type
        holder.descr.text = current.problem
        holder.addr.text = current.address
    }
}

class NewsViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    val title:TextView = itemView.findViewById(R.id.infoWindowTitle)
    val type:TextView = itemView.findViewById(R.id.infoWindowType)
    val descr:TextView = itemView.findViewById(R.id.infoWindowDescr)
    val addr:TextView = itemView.findViewById(R.id.infoWindowAddress)
}