package com.juni.desafiopeliculas.view.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.juni.desafiopeliculas.domain.model.MovieModel

@Composable
fun ListScreen(viewModel: ListMovieViewModel, selectMovie: (MovieModel) -> Unit) {

    val uiState by viewModel.uiStateList.collectAsState()

    when (uiState) {
        ListUiState.Loading -> {
            Box {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is ListUiState.Success -> {
            val listMovie = (uiState as ListUiState.Success).movieModelList
            ListMovies(listMovie) {
                selectMovie(it)
            }
        }

        is ListUiState.Error -> {

            Box(modifier = Modifier.background(Color.DarkGray)) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "ocurrio un error",
                    color = Color.White
                )
            }
        }

    }


}

@Composable
fun ListMovies(movieModelList: List<MovieModel>, selectMovie: (MovieModel) -> Unit) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(movieModelList) {
            ListItem(movieModel = it, selectMovie)
        }
    }
}


@Composable
fun ListItem(movieModel: MovieModel, selectMovie: (MovieModel) -> Unit, modifier: Modifier = Modifier) {
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