package com.example.newsapp.interfaces

import com.example.newsapp.data_models.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //http://newsapi.org/v2/everything?q=bitcoin&from=2020-04-18&sortBy=publishedAt&apiKey=e2da95559a7945a6994224e8966277ba
    @GET("v2/everything")
     fun getNews(
        @Query("q") requestedNews: String,
        @Query("apiKey") apiKey : String
    ): Call<NewsModel>
}