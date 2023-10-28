package com.example.bankmandiri.Data.Remote

import com.example.bankmandiri.Data.Response.NewResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("https://newsapi.org/v2/top-headlines?country=id&apiKey=e46a85e96bcc486389aae652641ff02e")
    fun getBerita() : Call<NewResponse>
}