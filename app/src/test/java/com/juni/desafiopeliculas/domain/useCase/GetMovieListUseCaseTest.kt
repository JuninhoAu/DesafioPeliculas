package com.juni.desafiopeliculas.domain.useCase


import com.juni.desafiopeliculas.common.DatabaseCheckResult
import com.juni.desafiopeliculas.data.repository.GetMovieListRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMovieListUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: GetMovieListRepository
    private lateinit var useCase: GetMovieListUseCase



    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        useCase = GetMovieListUseCase(repository)
    }

    @Test
    fun `given a request when checkMovieList is calling then return checkMovieList`() = runBlocking {
        //Given
        coEvery { repository.checkMovieList() } returns DatabaseCheckResult.Exists
        //When
        useCase.checkMovieList()
        //Then
        coVerify(exactly = 1) { repository.checkMovieList() }
    }



}