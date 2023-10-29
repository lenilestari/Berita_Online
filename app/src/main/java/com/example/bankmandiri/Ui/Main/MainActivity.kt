package com.example.bankmandiri.Ui.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.bankmandiri.Data.Remote.ApiClient
import com.example.bankmandiri.Data.Response.ArticlesItem
import com.example.bankmandiri.Data.Response.ArticlesItem2
import com.example.bankmandiri.Data.Response.NewResponse
import com.example.bankmandiri.Data.Response.ResponseBerita2
import com.example.bankmandiri.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NewsAdapter
    private lateinit var adapter2: HeadlineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NewsAdapter {}
        adapter2 = HeadlineAdapter {}

        initRecyclerView()
        setupHeadlineViewPager()
        getNews()
        getNews2()
    }

    private fun getNews2() {
        ApiClient.create().getBerita2().enqueue(object : Callback<ResponseBerita2> {
            override fun onResponse(call: Call<ResponseBerita2>, response: Response<ResponseBerita2>) {
                if (response.isSuccessful) {
                    val articles: List<ArticlesItem2> = response.body()?.articles?.filterNotNull() ?: emptyList()
                    adapter2.setNews2(articles)
                }

                Log.d("MainActivity2", response.body()?.totalResults.toString())
            }

            override fun onFailure(call: Call<ResponseBerita2>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun initRecyclerView() {
        val rvNews = findViewById<RecyclerView>(R.id.rvNews)
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = adapter
    }

    private fun setupHeadlineViewPager() {
        val viewPager: ViewPager2 = findViewById(R.id.rvNews2)
        viewPager.adapter = adapter2
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    private fun getNews() {
        ApiClient.create().getBerita().enqueue(object : Callback<NewResponse> {
            override fun onResponse(call: Call<NewResponse>, response: Response<NewResponse>) {
                if (response.isSuccessful) {
                    val articles: List<ArticlesItem> = response.body()?.articles?.filterNotNull() ?: emptyList()
                    adapter.setNews(articles) // Gunakan adapter untuk data dari getBerita
                }

                Log.d("MainActivity", response.body()?.totalResults.toString())
            }

            override fun onFailure(call: Call<NewResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
