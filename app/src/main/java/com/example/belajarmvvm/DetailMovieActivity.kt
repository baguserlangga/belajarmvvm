package com.example.belajarmvvm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.belajarmvvm.Adapter.MovieAdapter
import com.example.belajarmvvm.Model.DetailMovieModel
import com.example.belajarmvvm.ViewModel.MovieDetailViewModel
import com.example.belajarmvvm.databinding.ActivityDetailMovieBinding

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
//        val ss:String = Intent().getStringExtra("idmovie").toString()
//        val idmovie = Intent().getStringExtra("idmovie").toString();
        // getting the string back
//        var bundle = Intent().extras
        val smovieids:String = Intent().getStringExtra("idmovie").toString()

//        val movieid = bundle!!.getString("idmovie", "100");
        Log.d("okehbismillah", "onCreate: "+smovieids)
        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        viewModel.getPopularMovies("500")

        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            moviesnih.addAll(movieList)
            binding.textViewtitle.text = moviesnih[0].title
            binding.textViewIsiReleaseDate.text = moviesnih[0].releaseDate
            binding.textViewIsiOverView.text = moviesnih[0].overview
            Glide.with(binding.imageMovie)
                .load("https://image.tmdb.org/t/p/w500" + moviesnih[0].posterPath)
                .into(binding.imageMovie)
            Glide.with(binding.imageBackdrop)
                .load("https://image.tmdb.org/t/p/w500" + moviesnih[0].backdropPath)
                .into(binding.imageBackdrop)

        })

    }
}