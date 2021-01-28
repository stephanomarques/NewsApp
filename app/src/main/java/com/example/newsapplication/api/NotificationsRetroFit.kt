package com.example.newsapplication.api

import com.example.newsapplication.entities.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NotificationsRetroFit {

    companion object{
        private val retrofit2 by lazy {
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        val api by lazy {
            retrofit2.create(NotificationAPI::class.java)
        }

    }

}