package com.anurag.newsapiapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "188c52504f4f48f28876a36613238d15"


//https://newsapi.org/v2/top-headlines?country=in&apiKey=188c52504f4f48f28876a36613238d15

interface NewsInterface{


    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadline(@Query("country")country: String, @Query("page")page: Int): Call<News>
}


object NewsServices {
    val newInstance: NewsInterface
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newInstance= retrofit.create(NewsInterface::class.java)
    }
}


//http://newsapi.org/v2/top-headlines?apiKey=188c52504f4f48f28876a36613238d15&country=in&page=1