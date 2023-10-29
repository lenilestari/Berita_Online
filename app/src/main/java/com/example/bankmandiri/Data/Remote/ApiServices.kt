package com.example.bankmandiri.Data.Remote

import com.example.bankmandiri.Data.Response.NewResponse
import com.example.bankmandiri.Data.Response.ResponseBerita2
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("https://newsapi.org/v2/top-headlines?country=us&apiKey=e46a85e96bcc486389aae652641ff02e")
    fun getBerita() : Call<NewResponse>

    @GET("https://newsapi.org/v2/everything?q=bitcoin&apiKey=e46a85e96bcc486389aae652641ff02e")
    fun getBerita2() : Call<ResponseBerita2>

}