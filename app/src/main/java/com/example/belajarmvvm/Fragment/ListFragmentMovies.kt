package com.example.belajarmvvm.Fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belajarmvvm.Adapter.MovieAdapter
import com.example.belajarmvvm.Result
import com.example.belajarmvvm.ViewModel.MovieViewModel
import com.example.belajarmvvm.databinding.FragmentListMoviesBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragmentMovies.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragmentMovies : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentListMoviesBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter : MovieAdapter
    var moviesnih :ArrayList<Result> =ArrayList<Result>()
    private lateinit var lm : LinearLayoutManager
    var page = 1
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
        binding = FragmentListMoviesBinding.inflate(layoutInflater)
        lm  = LinearLayoutManager(requireContext())
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
        viewModel.observeMovieLiveData().observe(requireActivity(), Observer { movieList ->
            moviesnih.addAll(movieList)
            movieAdapter.setMovieList(moviesnih)
        })
         return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragmentMovies.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragmentMovies().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
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