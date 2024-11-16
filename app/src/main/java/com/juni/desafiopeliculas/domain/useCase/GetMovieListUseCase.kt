package com.juni.desafiopeliculas.domain.useCase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.juni.desafiopeliculas.common.ResultType
import com.juni.desafiopeliculas.data.local.MovieEntity
import com.juni.desafiopeliculas.data.repository.GetMovieListRepository
import com.juni.desafiopeliculas.domain.mapper.toDomain
import com.juni.desafiopeliculas.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val repository: GetMovieListRepository) {

    suspend fun getListFromApi(): ResultType<Boolean, String> {
        return repository.getMovieList()
    }

    fun getListFromBD(): Flow<List<MovieModel>> {
        return repository.getLocalMovies()
    }

    fun getListMoviePaging(): Flow<PagingData<MovieModel>> = Pager(
        PagingConfig(
            pageSize = 10,
            prefetchDistance = 2
        )
    ) {
        repository.getLocalMoviesPaging()
    }.flow.map { value: PagingData<MovieEntity> ->
        value.map { movieEntity: MovieEntity ->
            movieEntity.toDomain()
        }
    }

}