package com.example.newsapplication.entities

import com.google.gson.annotations.SerializedName

data class ResponseModel(
        @SerializedName("totalArticles")
        val totalArticles: Int,

        @SerializedName("articles")
        val allNews: List<News>? = null
)

