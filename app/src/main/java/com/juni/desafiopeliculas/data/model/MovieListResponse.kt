package com.juni.desafiopeliculas.data.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("results")
    val movieListResponse: List<MovieResponse>
)