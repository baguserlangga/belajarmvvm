package com.example.belajarmvvm.ViewModel

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belajarmvvm.Model.DetailMovieModel
import com.example.belajarmvvm.Model.Movies
import com.example.belajarmvvm.Result
import com.example.belajarmvvm.Service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class MovieDetailViewModel : ViewModel() {
    private var movieLiveData =  MutableLiveData<List<DetailMovieModel>>()
    fun getPopularMovies(page:Int) {
        val params: MutableMap<String, String> = HashMap()
        params["api_key"] = "7916ace8a965a1c3413cd5231af30364"
       // getting the bundle back from the android
        val idmovie = Intent().getStringExtra("idmovie").toString();
        // getting the string back
//        val movieid = bundle!!.getString("idmovie", "100");

        RetrofitInstance.api.getDetailMovies().enqueue(object  :
            Callback<DetailMovieModel> {
            override fun onResponse(call: Call<DetailMovieModel>, response: Response<DetailMovieModel>) {
                if (response.body()!=null){
                    if(movieLiveData!=null)
                    {
                        val m =DetailMovieModel(response.body()!!.backdropPath,
                            response.body()!!.originalLanguage,
                            response.body()!!.originalTitle,
                            response.body()!!.posterPath,
                            response.body()!!.overview,
                            response.body()!!.productionCompanies,
                            response.body()!!.productionCountries,
                            response.body()!!.releaseDate,
                            response.body()!!.revenue,
                            response.body()!!.runtime,
                            response.body()!!.spokenLanguages,
                            response.body()!!.status,
                            response.body()!!.tagline,
                            response.body()!!.title,
                            response.body()!!.video,
                            response.body()!!.voteAverage,
                            response.body()!!.voteCount,
                        )
                        val array = ArrayList<DetailMovieModel>()
                        array.add(m)
                        movieLiveData.value=array

                    }
                    else{
                        return
                    }

                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<DetailMovieModel>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : LiveData<List<DetailMovieModel>> {
        return movieLiveData
    }
}