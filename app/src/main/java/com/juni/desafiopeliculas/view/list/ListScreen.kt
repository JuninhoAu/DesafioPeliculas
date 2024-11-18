package com.juni.desafiopeliculas.view.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.juni.desafiopeliculas.common.DatabaseCheckResult
import com.juni.desafiopeliculas.domain.model.MovieModel

@Composable
fun ListScreen(viewModel: ListMovieViewModel, selectMovie: (MovieModel) -> Unit) {

    val checkMovieList by viewModel.checkMovieList.collectAsState(initial = DatabaseCheckResult.NoData)

    val movies: LazyPagingItems<MovieModel> = viewModel.moviePagingData.collectAsLazyPagingItems()

    ListBodyPage(checkMovieList, movies, selectMovie)
}


@Composable
fun ListBodyPage(
    checkMovieList: DatabaseCheckResult,
    movieListPaging: LazyPagingItems<MovieModel>,
    selectMovie: (MovieModel) -> Unit,
) {

    when (checkMovieList) {

        DatabaseCheckResult.Exists -> {
            ListMovie(movieListPaging = movieListPaging, selectMovie = selectMovie)
        }

        is DatabaseCheckResult.Error -> {
            ShowErrorScreen(message = "ocurrio un error al leer la BD")
        }

        DatabaseCheckResult.NoData -> {
            ShowErrorScreen(message = "no hay data en la  BD")
        }

        DatabaseCheckResult.Loading -> {
            ShowCircularProgress()
        }
    }


}


@Composable
fun ListMovie(movieListPaging: LazyPagingItems<MovieModel>, selectMovie: (MovieModel) -> Unit) {
    LazyColumn {
        items(
            count = movieListPaging.itemCount,
            key = movieListPaging.itemKey { movie -> movie.id },
            contentType = movieListPaging.itemContentType { "movies" }
        ) { index: Int ->
            val movie: MovieModel? = movieListPaging[index]
            movie?.let { ListItem1(movieModel = it, selectMovie) }
        }

    }

    CheckStatusPaging(movieListPaging)
}


@Composable
fun CheckStatusPaging(moviesLazyPaging: LazyPagingItems<MovieModel>) {

    when {

        moviesLazyPaging.loadState.hasError -> {
            ShowErrorScreen(message = "ocurrio un error al hacer la paginacion")
        }

        else -> {
            if (moviesLazyPaging.loadState.append is LoadState.Loading) {
                ShowInformativeScreen(message = "cargando mas datos")
            }

        }
    }

}


@Composable
fun ListItem1(
    movieModel: MovieModel,
    selectMovie: (MovieModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier
        .clickable {
            selectMovie(movieModel)
        }
        .padding(16.dp)
        .height(250.dp)) {
        Icon(
            painter = painterResource(android.R.drawable.ic_menu_send),
            contentDescription = "",
            modifier = Modifier.size(40.dp)
        )
        Column(modifier.fillMaxWidth()) {
            Text(
                text = movieModel.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
            )
            Text(
                text = movieModel.overview,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .height(25.dp)
                    .fillMaxWidth()
            )

        }
    }

}

@Composable
fun ShowCircularProgress() {
    Box {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ShowInformativeScreen(message: String) {
    Box(Modifier.background(Color.Red)) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = message,
            color = Color.White
        )
    }
}

@Composable
fun ShowErrorScreen(message: String) {
    Box(modifier = Modifier.background(Color.DarkGray)) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = message,
            color = Color.White
        )
    }
}