package com.juni.desafiopeliculas.view.list

import com.juni.desafiopeliculas.view.model.Movie

sealed interface ListUiState {
    data object Loading : ListUiState
    data class Error(val throwable: Throwable) : ListUiState
    data class Success(val movieList: List<Movie>) : ListUiState
}