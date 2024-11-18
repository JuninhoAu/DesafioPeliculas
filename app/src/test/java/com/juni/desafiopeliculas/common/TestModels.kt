package com.juni.desafiopeliculas.common

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.juni.desafiopeliculas.data.local.MovieEntity
import com.juni.desafiopeliculas.data.model.MovieListResponse
import com.juni.desafiopeliculas.data.model.MovieResponse
import com.juni.desafiopeliculas.domain.model.MovieModel


fun movieResponse() = MovieResponse(1, "", "", "", "", 2.3)
fun movieEntity() = MovieEntity(1, "", "", "", "", 2.3)
fun movieModel() = MovieModel(
    id = 1,
    title = "Movie 1",
    posterPath = "",
    voteAverage = "",
    releaseDate = "",
    overview = ""
)

fun movieListResponse() = MovieListResponse(listOf(movieResponse()))

