package com.juni.desafiopeliculas.view.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.juni.desafiopeliculas.domain.model.MovieModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(movieModel: MovieModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            GlideImage(
                "https://image.tmdb.org/t/p/w500${movieModel.posterPath}",
                contentDescription = "place image",
                modifier = Modifier
                    .width(120.dp)
                    .height(200.dp),
                contentScale = ContentScale.Fit

            )

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