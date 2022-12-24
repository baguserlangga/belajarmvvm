package com.example.belajarmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belajarmvvm.Model.DetailMovieModel
import com.example.belajarmvvm.ViewModel.MovieDetailViewModel
import com.example.belajarmvvm.ViewModel.MovieViewModel
import com.example.belajarmvvm.databinding.ActivityDetailMovieBinding
import com.example.belajarmvvm.databinding.ActivityMainBinding

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailMovieBinding
    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var movieAdapter : MovieAdapter
    var moviesnih :ArrayList<DetailMovieModel> =ArrayList<DetailMovieModel>()
    val  lm = LinearLayoutManager(this)
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        viewModel.getPopularMovies(page)

        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            moviesnih.addAll(movieList)
            binding.textViewtitle.text = moviesnih[0].title
            binding.textViewIsiReleaseDate.text = moviesnih[0].releaseDate
            binding.textViewIsiOverView.text = moviesnih[0].overview

        })

    }
}