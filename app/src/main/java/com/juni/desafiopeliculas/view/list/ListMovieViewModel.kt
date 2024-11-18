package com.juni.desafiopeliculas.view.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.juni.desafiopeliculas.common.DatabaseCheckResult
import com.juni.desafiopeliculas.domain.model.MovieModel
import com.juni.desafiopeliculas.domain.useCase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(useCase: GetMovieListUseCase) : ViewModel() {

    private val _checkMovieList = MutableStateFlow<DatabaseCheckResult>(DatabaseCheckResult.Loading)
    val checkMovieList: StateFlow<DatabaseCheckResult> = _checkMovieList

    val moviePagingData: Flow<PagingData<MovieModel>> =
        useCase.getListMoviePaging().cachedIn(viewModelScope)


    init {
        viewModelScope.launch {
            val checkMovieList = useCase.checkMovieList()

            when (checkMovieList) {

                DatabaseCheckResult.Exists -> _checkMovieList.value = DatabaseCheckResult.Exists

                DatabaseCheckResult.Error -> _checkMovieList.value = DatabaseCheckResult.Error

                DatabaseCheckResult.NoData -> _checkMovieList.value = DatabaseCheckResult.NoData

                DatabaseCheckResult.Loading -> _checkMovieList.value = DatabaseCheckResult.Loading
            }

        }
    }

}