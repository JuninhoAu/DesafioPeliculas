package com.juni.desafiopeliculas.view.list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juni.desafiopeliculas.view.model.Movie
import kotlinx.coroutines.launch

class ListMovieViewModel : ViewModel() {

    private val _movieList = mutableStateListOf<Movie>()
    val movieList: List<Movie> = _movieList

    init {
        viewModelScope.launch {
            _movieList.add(Movie("batman","path_poster","5","12/12/12","es un superheroe"))
            _movieList.add(Movie("superman","path_poster","4","12/12/16","viene de otro planeta"))
            _movieList.add(Movie("wonder woman","path_poster","3","12/12/13","es una amazona"))
            _movieList.add(Movie("el aro","path_poster","2","12/12/16","es de terror"))
            _movieList.add(Movie("heman","path_poster","1","12/12/20","el mas fuerte"))
            _movieList.add(Movie("gamera","path_poster","5","12/12/24","una tortuga gigante"))

        }
    }

}