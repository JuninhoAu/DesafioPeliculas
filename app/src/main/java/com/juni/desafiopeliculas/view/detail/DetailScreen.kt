package com.juni.desafiopeliculas.view.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.juni.desafiopeliculas.domain.model.MovieModel

@Composable
fun DetailScreen(movieModel: MovieModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = movieModel.title)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = movieModel.voteAverage)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = movieModel.releaseDate)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = movieModel.overview)

        }
    }

}