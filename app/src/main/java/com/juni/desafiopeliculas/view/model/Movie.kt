package com.juni.desafiopeliculas.view.model

import java.io.Serializable

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: String,
    val releaseDate: String,
    val overview: String
) : Serializable