package com.example.belajarmvvm

import com.example.belajarmvvm.Model.DetailMovieModel
import com.example.belajarmvvm.Model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MoviesApi {
    @GET("movie/popular?")
    fun getPopularMovies( @QueryMap params : Map<String,String>) : Call<Movies>
    @GET("movie/{movie_id}?")
    fun getDetailMovies(@Path("movie_id") idmovie:String,@QueryMap params : Map<String,String> ) : Call<DetailMovieModel>
    @GET("search/movie?")
    fun getSearchMovie( @QueryMap params : Map<String,String>) : Call<Movies>
//    api_key={api_key}&query=Jack+Reacher
}