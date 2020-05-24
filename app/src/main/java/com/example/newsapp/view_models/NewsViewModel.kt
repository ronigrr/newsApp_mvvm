package com.example.newsapp.view_models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.newsapp.data_models.NewsModel
import com.example.newsapp.repositories.NewsRepository
import java.net.CacheRequest

class NewsViewModel : ViewModel() {

    var viewModelRequestedNews :MutableLiveData<String> = MutableLiveData()
    private var mNews : LiveData<NewsModel>? = null
    private var mContext: Context? = null

    fun getArticles() : LiveData<NewsModel>{
        if (mNews == null){
            mNews = MutableLiveData()
            loadNews()
        }
        return mNews as LiveData<NewsModel>
    }


    fun setViewModelRequestedNews(requestedNews : String ="usa"){
        if (viewModelRequestedNews.value != requestedNews)
            viewModelRequestedNews.value = requestedNews
    }

    private fun loadNews(){
        mContext?.let {
            mNews = Transformations.switchMap(viewModelRequestedNews){// listening to viewModelRequestedNews string and if it changes (by calling setRequestedNews) it will trigger the code inside {} and return the NewsModel2 object
                reqNews->
                NewsRepository.getNews(reqNews,it)
            }
        }
    }

    fun setContext(context: Context){
        mContext = context
    }

}