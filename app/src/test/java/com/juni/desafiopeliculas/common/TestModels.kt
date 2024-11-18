package com.juni.desafiopeliculas.common

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.juni.desafiopeliculas.data.local.MovieEntity
import com.juni.desafiopeliculas.data.model.MovieListResponse
import com.juni.desafiopeliculas.data.model.MovieResponse
import com.juni.desafiopeliculas.domain.model.MovieModel


fun movieResponse() = MovieResponse(1, "", "", "", "", 2.3)
fun movieEntity() = MovieEntity(1, "", "", "", "", 2.3)
fun movieModel() = MovieModel(
    id = 1,
    title = "Movie 1",
    posterPath = "",
    voteAverage = "",
    releaseDate = "",
    overview = ""
)

fun movieListEntity() = listOf(movieEntity())
fun movieListResponse() = MovieListResponse(listOf(movieResponse()))
fun movieListEmptyResponse() = MovieListResponse(emptyList())
fun movieListModel() = listOf(movieModel())


 val fakePagingSource = object : PagingSource<Int, MovieEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return LoadResult.Page(
            data = movieListEntity(),
            prevKey = null,
            nextKey = 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return null
    }
}

class FakePagingSource(private val data: List<MovieEntity>) : PagingSource<Int, MovieEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? = null
}

