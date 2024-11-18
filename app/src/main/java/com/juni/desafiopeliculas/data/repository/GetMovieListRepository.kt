package com.juni.desafiopeliculas.data.repository

import androidx.paging.PagingSource
import com.juni.desafiopeliculas.common.DatabaseCheckResult
import com.juni.desafiopeliculas.common.ResultType
import com.juni.desafiopeliculas.data.local.MovieDao
import com.juni.desafiopeliculas.data.local.MovieEntity
import com.juni.desafiopeliculas.data.network.MovieService
import com.juni.desafiopeliculas.domain.mapper.toData
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieListRepository @Inject constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao
) {

    suspend fun checkMovieList(): DatabaseCheckResult {

        val movieList = movieService.getMovies()
        if (movieList is ResultType.Success && movieList.data.isNotEmpty()) {
            movieDao.insertMovies(movieList.data.map { it.toData() })
        }
        return if (hasMovies()) DatabaseCheckResult.Exists else DatabaseCheckResult.NoData

    }

    private suspend fun hasMovies(): Boolean {
        return movieDao.getMovies().firstOrNull()?.isNotEmpty() == true
    }


    fun getMovieListPaging(): PagingSource<Int, MovieEntity> = movieDao.getMoviesPaged()

}
