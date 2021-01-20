package com.example.newsapplication.entities

data class News(
        val title: String,
        val description: String,
        val content: String,
        val url: String,
        val image: String,
        val publishedAt: String,
        val source: Source
)

data class Source(
        val name: String,
        val source_url: String
)


