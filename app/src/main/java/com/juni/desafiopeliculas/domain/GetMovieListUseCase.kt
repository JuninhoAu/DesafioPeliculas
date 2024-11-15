package com.juni.desafiopeliculas.domain

import com.juni.desafiopeliculas.common.ResultType
import com.juni.desafiopeliculas.data.repository.GetMovieListRepository
import com.juni.desafiopeliculas.view.model.Movie

class GetMovieListUseCase(private val repository: GetMovieListRepository) {
    suspend operator fun invoke(): ResultType<List<Movie>,String> {
        return repository.getMovieList()
    }
}