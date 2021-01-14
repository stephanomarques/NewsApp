package com.example.newsapplication.entities

import android.net.Uri
import android.widget.ImageView

data class News(
    val id: Int,
    val image: Uri,
    val title: String,
    val source: String,
    //val type: String
)
