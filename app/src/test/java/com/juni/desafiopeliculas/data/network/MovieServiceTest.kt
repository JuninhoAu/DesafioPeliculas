package com.juni.desafiopeliculas.data.network

import com.juni.desafiopeliculas.common.ResultType
import com.juni.desafiopeliculas.common.movieListResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class MovieServiceTest{

    @RelaxedMockK
    lateinit var mockApiClient: MovieApiClient

    private lateinit var movieService: MovieService

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        movieService = MovieService()
    }


    @Test
    fun `given a request when getMovies is calling then return a success`() = runBlocking {

        val mockResponse = Response.success(movieListResponse())

        coEvery{mockApiClient.getMovies()} returns mockResponse

        val result = movieService.getMovies()

        assert(result is ResultType.Success)
        val data = (result as ResultType.Success).data
        assert(data.isNotEmpty())
    }

    @Test
    fun `given a request when getMovies is calling then return a failure`() = runBlocking {

        val mockResponse = Response.success(movieListResponse())

        coEvery{mockApiClient.getMovies()} returns mockResponse

        val result = movieService.getMovies()

        assert(result is ResultType.Success)
        val data = (result as ResultType.Success).data
        assert(data.isNotEmpty())
    }

}