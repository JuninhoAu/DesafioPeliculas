package com.juni.desafiopeliculas.view.list

import com.juni.desafiopeliculas.domain.model.MovieModel

sealed interface ListUiState {
    data object Loading : ListUiState
    data class Error(val throwable: Throwable) : ListUiState
    data class Success(val movieModelList: List<MovieModel>) : ListUiState
}