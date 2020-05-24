package com.example.newsapp.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.newsapp.data_models.NewsModel
import com.example.newsapp.extra.Constants
import com.example.newsapp.interfaces.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

object NewsRepository {

    fun getNews(requestedNews : String,context: Context) : LiveData<NewsModel>{
        return object : LiveData<NewsModel>(){
            override fun onActive() {
                super.onActive()
                try {
                    val retrofit : Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build()

                    val service : ApiService = retrofit.create<ApiService>(ApiService::class.java)

                    val listCall : Call<NewsModel> = service.getNews(requestedNews,Constants.API_KEY)

                    listCall.enqueue(object : Callback<NewsModel>{
                        override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                            Log.i("no"," no success getting news")
                        }
                        override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>
                        ) {
                            val newsModel : NewsModel? = response.body()
                            value = newsModel
                        }
                    })
                }catch (e:Exception){
                    Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}