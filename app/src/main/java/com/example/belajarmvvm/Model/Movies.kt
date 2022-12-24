package com.example.belajarmvvm.Model

import com.example.belajarmvvm.Result

data class Movies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)