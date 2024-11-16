package com.juni.desafiopeliculas.domain.mapper

import com.juni.desafiopeliculas.data.local.MovieEntity
import com.juni.desafiopeliculas.data.model.MovieResponse
import com.juni.desafiopeliculas.domain.model.MovieModel


fun MovieEntity.toDomain() =
    MovieModel(id, title, posterPath, voteAverage.toString(), releaseDate, overview)

fun MovieResponse.toData() =
    MovieEntity(id, overview, posterPath, releaseDate, title, voteAverage)