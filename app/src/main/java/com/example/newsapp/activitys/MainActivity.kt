package com.example.newsapp.activitys

import android.inputmethodservice.Keyboard
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsListAdapter
import com.example.newsapp.data_models.ArticleModel
import com.example.newsapp.view_models.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //private lateinit var mNewsViewModel: NewsViewModel
    private lateinit var mRecyclerView: RecyclerView
    private var mArticleList: List<ArticleModel> = ArrayList()
    private lateinit var mRecyclerViewAdapter: NewsListAdapter
    private lateinit var mNewsViewModel : NewsViewModel
    lateinit var requestedNews: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.vRecycler)
        initRecyclerView()
        mNewsViewModel = this.let{ViewModelProvider(this).get(NewsViewModel::class.java)}
        //mNewsViewModel.setViewModelRequestedNews()
        mNewsViewModel.setContext(this.applicationContext)


        mNewsViewModel.viewModelRequestedNews.observe(this, Observer { req->
            requestedNews=req
            if(!requestedNews.isBlank()){
                mNewsViewModel.setViewModelRequestedNews(requestedNews)
            }
        })

        mNewsViewModel.getArticles()?.observe(this,Observer{news ->
            mArticleList = news.articles
            if (mArticleList.isEmpty()) {
                Toast.makeText(this,"No News available on this topic",Toast.LENGTH_SHORT).show()
            }
            mRecyclerViewAdapter = NewsListAdapter(mArticleList)
            mRecyclerView.adapter = mRecyclerViewAdapter
        })



        btn_search.setOnClickListener {
            requestedNews = et_newsToSearch.toString()
            if (requestedNews!= "") {
                mNewsViewModel.setViewModelRequestedNews(et_newsToSearch.text.toString())
                et_newsToSearch.text.clear()
                mRecyclerView.visibility= View.VISIBLE
            }
        }

    }
    private fun initRecyclerView() {

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


}
