package com.example.newsapplication.entities

class News {

    //User Markers
    data class News(
            val id: Int,
            val title: String,
            val type: String,
            val address: String,
            val lat: String,
            val lng: String,
            val problem: String,
            val user_id: Int
    )
}