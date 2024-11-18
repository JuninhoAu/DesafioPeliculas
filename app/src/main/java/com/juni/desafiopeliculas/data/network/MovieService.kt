package com.juni.desafiopeliculas.data.network

import com.juni.desafiopeliculas.common.ResultType
import com.juni.desafiopeliculas.data.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(){

    suspend fun getMovies(): ResultType<List<MovieResponse>, String> {
        return withContext(Dispatchers.IO) {
            try {
                val service: MovieApiClient =
                    RetrofitHelper.getRetrofit().create(MovieApiClient::class.java)
                val response = service.getMovies()
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.let {
                        ResultType.Success(it.movieListResponse)
                    } ?: ResultType.Failure("No data found")
                } else {
                    ResultType.Failure("error")

                }
            } catch (e: Exception) {
                ResultType.Failure(e.message ?: "exception")
            }

        }
    }
}