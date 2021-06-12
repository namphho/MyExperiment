package com.example.myexperiment.experiments.recycler_binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myexperiment.R
import com.example.myexperiment.databinding.ActivityRecyclerBindingBinding
import com.example.myexperiment.experiments.recycler_binding.model.Movie

class RecyclerBindingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRecyclerBindingBinding
    private lateinit var viewModel: RecyclerBindingModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_binding)
        viewModel = ViewModelProvider(this).get(RecyclerBindingModel::class.java)

        movieAdapter = MovieAdapter()
        binding.rcList.adapter = movieAdapter
        movieAdapter.listener = object : MovieAdapter.MovieAdapterListener{
            override fun onClickItem(movie: Movie) {
                //handle click here
            }
        }

        viewModel.liveDataMovies.observe(this, { movies ->
            movieAdapter.submitList(movies)
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        movieAdapter.listener = null
    }
}