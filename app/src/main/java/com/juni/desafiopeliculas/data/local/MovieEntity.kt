package com.juni.desafiopeliculas.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "MovieEntity")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
)