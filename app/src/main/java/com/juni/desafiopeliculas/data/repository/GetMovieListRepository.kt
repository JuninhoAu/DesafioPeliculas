package com.juni.desafiopeliculas.data.repository

import com.juni.desafiopeliculas.common.ResultType
import com.juni.desafiopeliculas.data.model.MovieResponse
import com.juni.desafiopeliculas.data.network.MovieService
import com.juni.desafiopeliculas.view.model.Movie

class GetMovieListRepository(private val movieService: MovieService) {

    suspend fun getMovieList(): ResultType<List<Movie>, String> {
        val movieList =  movieService.getMovies()
        return when (movieList) {
            is ResultType.Success -> {
                val movieListMap = movieList.data.map { movie ->
                    movie.toDomain()
                }
                ResultType.Success(movieListMap)
            }
            is ResultType.Failure -> {
                ResultType.Failure(movieList.error)
            }
        }
    }
}

fun MovieResponse.toDomain() =
    Movie(id, title, posterPath, voteAverage.toString(), releaseDate, overview)
