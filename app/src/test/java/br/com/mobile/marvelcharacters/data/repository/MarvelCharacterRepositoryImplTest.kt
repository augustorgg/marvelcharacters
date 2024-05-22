package br.com.mobile.marvelcharacters.data.repository

import br.com.mobile.marvelcharacters.data.model.MarvelApiDataResponse
import br.com.mobile.marvelcharacters.data.network.datasource.MarvelCharactersDetailDataSource
import br.com.mobile.marvelcharacters.data.service.DataResult
import br.com.mobile.marvelcharacters.data.utils.Logger
import br.com.mobile.marvelcharacters.domain.mapper.MarvelCharactersDetailMapper
import br.com.mobile.marvelcharacters.domain.model.Result
import br.com.mobile.marvelcharacters.domain.repository.MarvelCharacterRepository
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class MarvelCharacterRepositoryImplTest {

    @MockK
    private lateinit var dataSource: MarvelCharactersDetailDataSource
    private lateinit var repository: MarvelCharacterRepository
    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        repository = MarvelCharacterRepositoryImpl(dataSource, testDispatcher)
        mockkObject(Logger)
        every { Logger.logError(any(), any()) } just Runs
    }

    @Test
    fun `test getMarvelCharactersDetail success`() = runTest(testScheduler) {
        val mockResponse = mockk<MarvelApiDataResponse>(relaxed = true)
        val domainModelList = MarvelCharactersDetailMapper.mapFromDataModel(mockResponse)
        val expected = Result.Success(domainModelList.data?.results)

        coEvery { dataSource.getMarvelCharacterDetail() } returns DataResult.Success(mockResponse)

        val result = repository.getMarvelCharactersDetail()

        Assertions.assertEquals(expected, result)
        coVerify { dataSource.getMarvelCharacterDetail() }
    }

    @Test
    fun `test getMarvelCharactersDetail network error`() = runTest(testScheduler) {
        val errorMessage = "Network error"
        val statusCode = 404

        coEvery { dataSource.getMarvelCharacterDetail() } returns DataResult.NetworkError(
            errorMessage,
            statusCode
        )

        val result = repository.getMarvelCharactersDetail()

        Assertions.assertTrue(result is Result.NetworkError)
        Assertions.assertEquals(errorMessage, (result as Result.NetworkError).message)
        Assertions.assertEquals(statusCode, result.httpStatus)
        coVerify { dataSource.getMarvelCharacterDetail() }
    }

    @Test
    fun `test getMarvelCharactersDetail generic error`() = runTest(testScheduler) {
        val exception = Exception("Generic error")

        coEvery { dataSource.getMarvelCharacterDetail() } returns DataResult.GenericError(exception = exception)

        val result = repository.getMarvelCharactersDetail()

        Assertions.assertTrue(result is Result.GenericError)
        Assertions.assertEquals(exception, (result as Result.GenericError).exception)
        coVerify { dataSource.getMarvelCharacterDetail() }
    }

    @Test
    fun `test getMarvelCharactersDetail unexpected exception`() = runTest(testScheduler) {
        val exception = Exception("Unexpected error")

        coEvery { dataSource.getMarvelCharacterDetail() } throws exception

        val result = repository.getMarvelCharactersDetail()

        Assertions.assertTrue(result is Result.GenericError)
        Assertions.assertEquals(exception, (result as Result.GenericError).exception)
        coVerify { dataSource.getMarvelCharacterDetail() }
    }
}
