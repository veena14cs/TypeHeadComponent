package com.example.myapplication.service

import com.example.myapplication.response.Model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun getSuggestions(@Query("q") name: String): Call<Model>
}