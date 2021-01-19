package com.example.newsapplication.api

import com.example.newsapplication.entities.News
import com.example.newsapplication.entities.ResponseModel
import com.example.newsapplication.entities.User
import retrofit2.Call
import retrofit2.http.*

interface EndPoints {

    //Get News Top Headlines
    @GET("top-headlines?lang=en&token=33373c71ba6ddbba81aaf657a8772c3d&max=10")
    fun getTopNews(): Call<ResponseModel>

    //Get Specific User
    @GET("/news/public/api/news/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    //Get Specific User
    @GET("/news/public/api/userid_by_email/{email}")
    fun getUserIdByEmail(@Path("email") email: String): Call<List<User>>

    //Get All Markers
    @GET("/news/public/api/get_markers")
    fun getMarkers(): Call<List<News>>

    //Get All Markers of specific user id
    @GET("/news/public/api/get_markers/{id}")
    fun getMarkersById(@Path("id") id: Int): Call<List<News>>
}