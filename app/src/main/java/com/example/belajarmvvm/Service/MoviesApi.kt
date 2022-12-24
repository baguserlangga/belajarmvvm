package com.example.belajarmvvm

import com.example.belajarmvvm.Model.DetailMovieModel
import com.example.belajarmvvm.Model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MoviesApi {
    @GET("popular?")
    fun getPopularMovies( @QueryMap params : Map<String,String>) : Call<Movies>
    @GET("movie/500?api_key=7916ace8a965a1c3413cd5231af30364")
    fun getDetailMovies( ) : Call<DetailMovieModel>
    @GET("search/movie?")
    fun getSearchMovie( @Path("movie_is") movie_id : String) : Call<DetailMovieModel>
//    api_key={api_key}&query=Jack+Reacher
}