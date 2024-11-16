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
import com.juni.desafiopeliculas.domain.model.MovieModel

@Composable
fun ListScreen(viewModel: ListMovieViewModel, selectMovie: (MovieModel) -> Unit) {

    val movies: LazyPagingItems<MovieModel> = viewModel.moviePagingData.collectAsLazyPagingItems()

    ListBodyPage(movies, selectMovie)

}


@Composable
fun ListBodyPage(moviesLazyPaging: LazyPagingItems<MovieModel>, selectMovie: (MovieModel) -> Unit) {

    LazyColumn {
        items(
            count = moviesLazyPaging.itemCount,
            key = moviesLazyPaging.itemKey { movie -> movie.id },
            contentType = moviesLazyPaging.itemContentType { "movies" }
        ) { index: Int ->
            val movie: MovieModel? = moviesLazyPaging[index]
            movie?.let { ListItem(movieModel = it, selectMovie) }
        }

    }

    when {
        moviesLazyPaging.loadState.refresh is LoadState.Loading && moviesLazyPaging.itemCount == 0 -> {
            Box {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        moviesLazyPaging.loadState.refresh is LoadState.NotLoading && moviesLazyPaging.itemCount == 0 -> {
            Box(modifier = Modifier.background(Color.DarkGray)) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "me quede sin items que mostar",
                    color = Color.White
                )
            }
        }

        moviesLazyPaging.loadState.hasError -> {
            Box(modifier = Modifier.background(Color.DarkGray)) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "ocurrio un error",
                    color = Color.White
                )
            }
        }

        else -> {
            if (moviesLazyPaging.loadState.append is LoadState.Loading) {
                Box(Modifier.background(Color.Red)) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}


@Composable
fun ListItem(
    movieModel: MovieModel,
    selectMovie: (MovieModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier
        .clickable {
            selectMovie(movieModel)
        }
        .padding(16.dp)) {
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