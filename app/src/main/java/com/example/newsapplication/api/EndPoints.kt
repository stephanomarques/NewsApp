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

    //Get Business Headlines
    @GET("top-headlines?lang=en&token=33373c71ba6ddbba81aaf657a8772c3d&max=10&topic=business")
    fun getBusinessNews(): Call<ResponseModel>

    //Get Health Headlines
    @GET("top-headlines?lang=en&token=33373c71ba6ddbba81aaf657a8772c3d&max=10&topic=health")
    fun getHealthNews(): Call<ResponseModel>

    //Get Tech Headlines
    @GET("top-headlines?lang=en&token=33373c71ba6ddbba81aaf657a8772c3d&max=10&topic=technology")
    fun getTechnologyNews(): Call<ResponseModel>

    //Get Sports Headlines
    @GET("top-headlines?lang=en&token=33373c71ba6ddbba81aaf657a8772c3d&max=10&topic=sports")
    fun getSportsNews(): Call<ResponseModel>

}