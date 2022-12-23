package com.example.belajarmvvm

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieApi {
    @GET("popular?")
    fun getPopularMovies(@Query("api_key") api_key : String) : Call<Movies>
}