package com.example.myapplication.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.response.Item
import com.example.myapplication.response.Model
import com.example.myapplication.service.ApiService
import com.example.myapplication.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SuggestionRepository {
    private val apiService: ApiService = RetrofitClient.getClient().create(ApiService::class.java)

    fun fetchSuggestions(query: String): LiveData<List<Item>> {

        val suggestionLiveData = MutableLiveData<List<Item>>()
        apiService.getSuggestions(query).enqueue(object : Callback<Model> {
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                println("suggestionLiveData" + response)
                if (response.isSuccessful) {
                    suggestionLiveData.value = response.body()?.items
                } else {
                    println("suggestionLiveData Failed to fetch")
                }
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                println("suggestionLiveData Failed to fecth: ${t.message}")
                suggestionLiveData.value = null
            }
        })
        return suggestionLiveData
    }
}