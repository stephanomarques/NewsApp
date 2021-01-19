package com.example.newsapplication.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceBuilder {
    var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)

    private val gson = GsonBuilder()
        .serializeNulls()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gnews.io/api/v4/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client.build()).build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}