package com.juni.desafiopeliculas.view.list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juni.desafiopeliculas.common.ResultType
import com.juni.desafiopeliculas.domain.GetMovieListUseCase
import com.juni.desafiopeliculas.view.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListMovieViewModel(getMovieListUseCase: GetMovieListUseCase) : ViewModel() {

    private val _movieList = mutableStateListOf<Movie>()
    val movieList: List<Movie> = _movieList

    private val _uiStateList = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val uiStateList: StateFlow<ListUiState> = _uiStateList

    init {
        viewModelScope.launch {
            _uiStateList.value = ListUiState.Loading

            when (val movieNetWorkList = getMovieListUseCase.invoke()) {
                is ResultType.Success -> {
                    val list = movieNetWorkList.data
                    for (movie in list) {
                        _movieList.add(movie)
                    }
                    _uiStateList.value = ListUiState.Success(_movieList)
                }

                is ResultType.Failure -> {
                    _uiStateList.value = ListUiState.Error(Throwable(movieNetWorkList.error))
                }

            }

        }
    }

}