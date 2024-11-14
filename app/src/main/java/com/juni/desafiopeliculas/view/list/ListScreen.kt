package com.juni.desafiopeliculas.view.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.juni.desafiopeliculas.view.model.Movie

@Composable
fun ListScreen(ola: (Movie) -> Unit) {
    //val data by viewModel.placeList.observeAsState(emptyList())
    val list =
        listOf(
            Movie("batman", "url poster", "4", "12/12/2009", "una pelicula de batman"),
            Movie("superman", "url poster", "4", "12/12/2009", "una pelicula de batman"),
            Movie("robocop", "url poster", "4", "12/12/2009", "una pelicula de batman")
        )

    LazyColumn(Modifier.fillMaxSize()) {
        items(list) {
            ListItem(movie = it, ola)
        }
    }

}


@Composable
fun ListItem(movie: Movie, selectItem: (Movie) -> Unit, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier
        .clickable {
            selectItem(movie)
        }
        .padding(16.dp)) {
        Icon(
            painter = painterResource(android.R.drawable.ic_menu_send),
            contentDescription = "",
            modifier = Modifier.size(40.dp)
        )
        Column(modifier.fillMaxWidth()) {
            Text(
                text = movie.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
            )
            Text(
                text = movie.overview,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .height(25.dp)
                    .fillMaxWidth()
            )

        }
    }

}