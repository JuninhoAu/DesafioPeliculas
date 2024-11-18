package com.juni.desafiopeliculas.domain.model

import java.io.Serializable

data class MovieModel(
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: String,
    val releaseDate: String,
    val overview: String
) : Serializable