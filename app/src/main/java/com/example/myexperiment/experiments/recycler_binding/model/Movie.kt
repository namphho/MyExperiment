package com.example.myexperiment.experiments.recycler_binding.model

import com.google.gson.annotations.SerializedName

/**
 * Created by nampham on 5/7/21.
 */
data class Movie (
    val adult: Boolean? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    val genreIDS: List<Long>? = null,
    val id: Long? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,

    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    val voteCount: Long? = null
)

fun Movie.getImageBackDrops() : String{
    return "https://image.tmdb.org/t/p/w500/${this.backdropPath}"
}