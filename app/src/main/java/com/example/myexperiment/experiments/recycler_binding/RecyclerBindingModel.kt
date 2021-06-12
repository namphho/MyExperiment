package com.example.myexperiment.experiments.recycler_binding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myexperiment.experiments.recycler_binding.model.Movie
import com.example.myexperiment.experiments.recycler_binding.rest.RestClient
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Created by nampham on 4/17/21.
 */
class RecyclerBindingModel : ViewModel() {
    private var _movies = mutableListOf<Movie>()
    val movies: List<Movie>
        get() = _movies

    private var _liveDataMovies = MutableLiveData<List<Movie>>()
    val liveDataMovies: LiveData<List<Movie>>
        get() = _liveDataMovies


    fun getData() {
        viewModelScope.launch {
            val nowPlayMovies = async {
                RestClient.getInstance().API.listNowPlayMovies(language = "en-US", page = 1)
            }
            val topMovies = async {
                RestClient.getInstance().API.listNowTopRated(language = "en-US", page = 1)
            }
            val nowPlayResp = nowPlayMovies.await()
            val topMoviesResp = topMovies.await()

            nowPlayResp.results?.let {
                _movies.addAll(it)
                _liveDataMovies.postValue(_movies)
            }

        }
    }
}