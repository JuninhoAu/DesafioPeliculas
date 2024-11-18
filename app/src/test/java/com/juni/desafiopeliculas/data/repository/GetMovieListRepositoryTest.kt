package com.juni.desafiopeliculas.data.repository

import androidx.paging.PagingSource
import com.juni.desafiopeliculas.common.DatabaseCheckResult
import com.juni.desafiopeliculas.common.ResultType
import com.juni.desafiopeliculas.common.movieEntity
import com.juni.desafiopeliculas.common.movieResponse
import com.juni.desafiopeliculas.data.local.MovieDao
import com.juni.desafiopeliculas.data.local.MovieEntity
import com.juni.desafiopeliculas.data.network.MovieService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMovieListRepositoryTest {

    @RelaxedMockK
    lateinit var movieService: MovieService

    @RelaxedMockK
    lateinit var movieDao: MovieDao

    private lateinit var repository: GetMovieListRepository


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        repository = GetMovieListRepository(movieService, movieDao)

    }

    @Test
    fun `given a request when checkMovieList is calling then return affirmative confirmation `() = runBlocking {

        coEvery { movieService.getMovies() } returns ResultType.Success(listOf(movieResponse()))
        coEvery { movieDao.getMovies() } returns flowOf(listOf(movieEntity()))

        val result = repository.checkMovieList()

        assert(result is DatabaseCheckResult.Exists)

    }


    @Test
    fun `given a request when checkMovieList is calling then return negative confirmation `() = runBlocking {

        coEvery { movieService.getMovies() } returns ResultType.Success(listOf(movieResponse()))
        coEvery { movieDao.getMovies() } returns flowOf(emptyList())


        val response = repository.checkMovieList()

        assert(response == DatabaseCheckResult.NoData)

    }

    @Test
    fun `given a empty table in bd when checkMovieList is calling then return negative confirmation `() = runBlocking {

        coEvery { movieService.getMovies() } returns ResultType.Failure("error")
        coEvery { movieDao.getMovies() } returns flowOf(emptyList())

        val response = repository.checkMovieList()

        assert(response == DatabaseCheckResult.NoData)

    }



    @Test
    fun `given a request when getMovieListPaging is calling then return a pagingSource`() = runBlocking {

        val pagingSource = mockk<PagingSource<Int, MovieEntity>>()

        coEvery { movieDao.getMoviesPaged() } returns pagingSource

        val result = repository.getMovieListPaging()

        assert(result == pagingSource)
    }
}