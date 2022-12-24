package com.example.belajarmvvm.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belajarmvvm.Model.Movies
import com.example.belajarmvvm.Result
import com.example.belajarmvvm.Service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class MovieViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<List<Result>>()
    fun getPopularMovies(page:Int) {
        val params: MutableMap<String, String> = HashMap()
        params["api_key"] = "7916ace8a965a1c3413cd5231af30364"
        params["language"] = "en-US"
        params["page"] = page.toString()
        RetrofitInstance.api.getPopularMovies(params).enqueue(object  :
            Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.body()!=null){
                    if(movieLiveData!=null)
                    {
                        var m =movieLiveData.value

                        movieLiveData.value = response.body()!!.results

                    }
                    else{

                        movieLiveData.value = response.body()!!.results

                    }

                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }
    fun getsearch(Search:String) {
        val params: MutableMap<String, String> = HashMap()
        params["api_key"] = "7916ace8a965a1c3413cd5231af30364"
        params["language"] = "en-US"
        params["query"] = Search.toString()
        RetrofitInstance.api.getSearchMovie(params).enqueue(object  :
            Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.body()!=null){
                    if(movieLiveData!=null)
                    {
                        var m =movieLiveData.value

                        movieLiveData.value = response.body()!!.results

                    }
                    else{

                        movieLiveData.value = response.body()!!.results

                    }

                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : LiveData<List<Result>> {
        return movieLiveData
    }
}