package com.example.belajarmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belajarmvvm.ViewModel.MovieViewModel
import com.example.belajarmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter : MovieAdapter
    var moviesnih :ArrayList<Result> =ArrayList<Result>()
    val  lm = LinearLayoutManager(this)
    var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.getPopularMovies(page)
        binding.rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy>0)
                {
                    var vitem = lm.childCount
                    var litem = lm.findFirstCompletelyVisibleItemPosition()
                    var count = movieAdapter.itemCount
                    if(vitem+litem >=count)
                    {
                        loadmore()
                    }
                    Log.e("inivitem", "onScrolled: " + vitem.toString() )

                }

            }
        })
        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            moviesnih.addAll(movieList)
            movieAdapter.setMovieList(moviesnih)
        })
    }


    private fun prepareRecyclerView() {

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.getPopularMovies(page)
        movieAdapter = MovieAdapter()

        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.layoutManager = lm
        movieAdapter = MovieAdapter()
        binding.rvMovies.adapter = movieAdapter

    }
    fun loadmore()
    {
        Handler().postDelayed(
            {
                page+=1
                viewModel.getPopularMovies(page)
                viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
                    moviesnih.addAll(movieList)
                    movieAdapter.setMovieList(moviesnih)
                    movieAdapter.notifyDataSetChanged()
                })
            },5000
        )
    }
}