package com.juni.desafiopeliculas.domain

import android.util.Log
import com.juni.desafiopeliculas.common.ResultType
import com.juni.desafiopeliculas.data.repository.GetMovieListRepository
import com.juni.desafiopeliculas.view.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val repository: GetMovieListRepository) {
    suspend  fun getListFromApi(): ResultType<Boolean,String> {
        return repository.getMovieList()
    }
    fun getListFromBD(): Flow<List<Movie>> {
       return repository.getLocalMovies()
    }

}