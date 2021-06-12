package com.example.myexperiment.experiments.recycler_binding.model

/**
 * Created by nampham on 5/7/21.
 */
data class MovieResp (
    val dates: Dates? = null,
    val page: Long? = null,
    val results: List<Movie>? = null,
    val totalPages: Long? = null,
    val totalResults: Long? = null
)

data class Dates (
    val maximum: String? = null,
    val minimum: String? = null
)


