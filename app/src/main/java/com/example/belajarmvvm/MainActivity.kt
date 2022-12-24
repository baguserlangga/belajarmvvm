package com.example.belajarmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belajarmvvm.Adapter.MovieAdapter
import com.example.belajarmvvm.Fragment.DetailFragment
import com.example.belajarmvvm.Fragment.ListFragmentMovies
import com.example.belajarmvvm.ViewModel.MovieViewModel
import com.example.belajarmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter : MovieAdapter
    var moviesnih :ArrayList<Result> =ArrayList<Result>()
    var idmovie :Int?=null
    val  lm = LinearLayoutManager(this)
    var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragmentLayout ,  ListFragmentMovies())
                addToBackStack(null)
                commit()
            }



    }


    fun setFragment(posisi: Int) {

        if(posisi==1)
        {
            var firstFragment= ListFragmentMovies()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragmentLayout , firstFragment)
                addToBackStack(null)
                commit()
            }
        }
        if(posisi==2)
        {
            var firstFragment= DetailFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragmentLayout , firstFragment)
                addToBackStack(null)
                commit()
            }
        }
    }

}