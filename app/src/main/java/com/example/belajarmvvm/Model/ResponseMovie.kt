package com.example.moviesapps.Model

import com.example.belajarmvvm.Model.Movies
import com.google.gson.annotations.SerializedName

data class ResponseMovie(
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<Movies> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null
)