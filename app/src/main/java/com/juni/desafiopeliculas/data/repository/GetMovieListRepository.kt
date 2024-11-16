package com.juni.desafiopeliculas.data.repository

import androidx.paging.PagingSource
import com.juni.desafiopeliculas.common.ResultType
import com.juni.desafiopeliculas.data.local.MovieDao
import com.juni.desafiopeliculas.data.local.MovieEntity
import com.juni.desafiopeliculas.data.network.MovieService
import com.juni.desafiopeliculas.domain.mapper.toData
import com.juni.desafiopeliculas.domain.mapper.toDomain
import com.juni.desafiopeliculas.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieListRepository @Inject constructor (
    private val movieService: MovieService,
    private val movieDao: MovieDao
) {

    suspend fun getMovieList(): ResultType<Boolean, String> {
        val movieList = movieService.getMovies()
        return when (movieList) {
            is ResultType.Success -> {
                if (movieList.data.isNotEmpty()) {
                    val movieListMap = movieList.data.map { movie ->
                        movie.toData()
                    }
                    movieDao.insertMovies(movieListMap)
                    ResultType.Success(true)
                } else {
                    ResultType.Failure("lista vacia")
                }

            }

            is ResultType.Failure -> {
                ResultType.Failure(movieList.error)
            }
        }
    }

    fun getLocalMovies(): Flow<List<MovieModel>> = movieDao.getMovies().map {
        movieEntityList -> movieEntityList.map {
            movieEntity -> movieEntity.toDomain()
        }
    }

    fun getLocalMoviesPaging(): PagingSource<Int, MovieEntity> = movieDao.getMoviesPaged()

}
