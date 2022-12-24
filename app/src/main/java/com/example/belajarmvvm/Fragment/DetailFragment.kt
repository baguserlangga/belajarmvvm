package com.example.belajarmvvm.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.belajarmvvm.Adapter.MovieAdapter
import com.example.belajarmvvm.MainActivity
import com.example.belajarmvvm.Model.DetailMovieModel
import com.example.belajarmvvm.R
import com.example.belajarmvvm.ViewModel.MovieDetailViewModel
import com.example.belajarmvvm.databinding.ActivityDetailMovieBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    private lateinit var binding : ActivityDetailMovieBinding
    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var movieAdapter : MovieAdapter
    var moviesnih :ArrayList<DetailMovieModel> =ArrayList<DetailMovieModel>()
    private lateinit var   lm : LinearLayoutManager
    var page = 1

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val idmovies = (context as MainActivity).idmovie
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
//        val ss:String = Intent().getStringExtra("idmovie").toString()
//        val idmovie = Intent().getStringExtra("idmovie").toString();
        // getting the string back
//        var bundle = Intent().extras
        val smovieids:String = Intent().getStringExtra("idmovie").toString()
        lm=LinearLayoutManager(requireContext())
//        val movieid = bundle!!.getString("idmovie", "100");
        Log.d("okehbismillah", "onCreate: "+smovieids)
        viewModel = ViewModelProvider(requireActivity())[MovieDetailViewModel::class.java]
        viewModel.getPopularMovies(idmovies.toString())

        viewModel.observeMovieLiveData().observe(requireActivity(), Observer { movieList ->
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
        return (binding.root)


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}