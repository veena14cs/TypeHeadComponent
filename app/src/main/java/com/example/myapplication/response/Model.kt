package com.example.myapplication.response

data class Model(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)