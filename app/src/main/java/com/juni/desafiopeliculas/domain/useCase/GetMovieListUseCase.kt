package com.juni.desafiopeliculas.domain.useCase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.juni.desafiopeliculas.common.DatabaseCheckResult
import com.juni.desafiopeliculas.data.local.MovieEntity
import com.juni.desafiopeliculas.data.repository.GetMovieListRepository
import com.juni.desafiopeliculas.domain.mapper.toDomain
import com.juni.desafiopeliculas.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val repository: GetMovieListRepository) {

    suspend fun checkMovieList(): DatabaseCheckResult {
        return repository.checkMovieList()
    }

     suspend fun checkMovieList2(): DatabaseCheckResult {
         return repository.checkMovieList()
    }

    fun getListMoviePaging(): Flow<PagingData<MovieModel>> = Pager(
        PagingConfig(
            pageSize = 5,
            prefetchDistance = 2
        )
    ) {
        repository.getMovieListPaging()
    }.flow.map { value: PagingData<MovieEntity> ->
        value.map { movieEntity: MovieEntity ->
            movieEntity.toDomain()
        }
    }

}