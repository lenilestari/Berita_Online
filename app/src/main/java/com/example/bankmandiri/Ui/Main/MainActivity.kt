package com.example.bankmandiri.Ui.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankmandiri.Data.Remote.ApiClient
import com.example.bankmandiri.Data.Response.ArticlesItem
import com.example.bankmandiri.Data.Response.NewResponse
import com.example.bankmandiri.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NewsAdapter {}
        getNews()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val rvNews = findViewById<RecyclerView>(R.id.rvNews)

        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = adapter
    }


    private fun getNews() {
        ApiClient.create().getBerita().enqueue(object : Callback<NewResponse> {
            override fun onResponse(call: Call<NewResponse>, response: Response<NewResponse>) {
                if (response.isSuccessful) {
                    val articles: List<ArticlesItem> = response.body()?.articles?.filterNotNull() ?: emptyList()
                    adapter.setNews(articles)
                }

                Log.d("MainActivity", response.body()?.totalResults.toString())
            }

            override fun onFailure(call: Call<NewResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}