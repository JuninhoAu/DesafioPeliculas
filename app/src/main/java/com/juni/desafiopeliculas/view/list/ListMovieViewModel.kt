package com.juni.desafiopeliculas.view.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juni.desafiopeliculas.common.ResultType
import com.juni.desafiopeliculas.domain.useCase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(getMovieListUseCase: GetMovieListUseCase) :
    ViewModel() {

    private val _uiStateList = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val uiStateList: StateFlow<ListUiState> = _uiStateList

    init {
        viewModelScope.launch {
            _uiStateList.value = ListUiState.Loading
            val result = getMovieListUseCase.getListFromApi()
            when (result) {
                is ResultType.Success -> {
                    getMovieListUseCase.getListFromBD().catch {
                        _uiStateList.value = ListUiState.Error(Throwable("hubo un error"))
                    }.collect { movies ->
                        if (movies.isEmpty()) {
                            _uiStateList.value = ListUiState.Error(Throwable("lista vacia"))
                        } else {
                            _uiStateList.value = ListUiState.Success(movies)
                        }
                    }
                }

                is ResultType.Failure -> {
                    getMovieListUseCase.getListFromBD().catch {
                        _uiStateList.value = ListUiState.Error(Throwable("hubo un error"))
                    }.collect { movies ->
                        if (movies.isEmpty()) {
                            _uiStateList.value = ListUiState.Error(Throwable("lista vacia"))
                        } else {
                            _uiStateList.value = ListUiState.Success(movies)
                        }
                    }
                }

            }

        }
    }

}