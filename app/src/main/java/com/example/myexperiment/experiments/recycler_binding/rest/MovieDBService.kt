package com.example.myexperiment.experiments.recycler_binding.rest

import com.example.myexperiment.experiments.recycler_binding.model.MovieResp
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by nampham on 5/7/21.
 */
interface MovieDBService {
    @GET("movie/now_playing")
    suspend fun listNowPlayMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): MovieResp

    @GET("movie/top_rated")
    suspend fun listNowTopRated(
        @Query("language") language: String,
        @Query("page") page: Int,
    ) : MovieResp
}
