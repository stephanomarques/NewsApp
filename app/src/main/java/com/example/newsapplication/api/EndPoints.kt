package com.example.newsapplication.api

import com.example.newsapplication.entities.ResponseModel
import retrofit2.Call
import retrofit2.http.*

interface EndPoints {

    //Get News Top Headlines
    @GET("top-headlines?lang=en&token=ed10c890cfdbbb7b3f67e246b91bda40&max=10")
    fun getTopNews(): Call<ResponseModel>

    //Get Business Headlines
    @GET("top-headlines?lang=en&token=ed10c890cfdbbb7b3f67e246b91bda40&max=10&topic=business")
    fun getBusinessNews(): Call<ResponseModel>

    //Get Health Headlines
    @GET("top-headlines?lang=en&token=ed10c890cfdbbb7b3f67e246b91bda40&max=10&topic=health")
    fun getHealthNews(): Call<ResponseModel>

    //Get Tech Headlines
    @GET("top-headlines?lang=en&token=ed10c890cfdbbb7b3f67e246b91bda40&max=10&topic=technology")
    fun getTechnologyNews(): Call<ResponseModel>

    //Get Sports Headlines
    @GET("top-headlines?lang=en&token=ed10c890cfdbbb7b3f67e246b91bda40&max=10&topic=sports")
    fun getSportsNews(): Call<ResponseModel>

    //Get Science Headlines
    @GET("top-headlines?lang=en&token=ed10c890cfdbbb7b3f67e246b91bda40&max=10&topic=science")
    fun getScienceNews(): Call<ResponseModel>

    //Get Home Headlines
    @GET("top-headlines?lang=en&token=ed10c890cfdbbb7b3f67e246b91bda40&max=10&topic=world")
    fun getHomeNews(): Call<ResponseModel>

    //Get Entertainment Headlines
    @GET("top-headlines?lang=en&token=ed10c890cfdbbb7b3f67e246b91bda40&max=10&topic=entertainment")
    fun getEntertainmentNews(): Call<ResponseModel>

}