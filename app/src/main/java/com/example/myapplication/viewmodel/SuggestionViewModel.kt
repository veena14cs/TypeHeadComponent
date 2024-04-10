package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repo.SuggestionRepository
import com.example.myapplication.response.Item
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SuggestionViewModel: ViewModel(){

    private val suggestionRepository = SuggestionRepository()
    private var suggestionList: LiveData<List<Item>> = MutableLiveData<List<Item>>()

    private var searchJob: Job? = null

    fun getAutoCompleteSuggestions(query: String): LiveData<List<Item>>{
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            suggestionList = suggestionRepository.fetchSuggestions(query)
        }
        return suggestionList
    }
}