package br.com.mobile.marvelcharacters.data.network.datasource

import br.com.mobile.marvelcharacters.data.model.MarvelApiDataResponse
import br.com.mobile.marvelcharacters.data.model.MarvelCharactersDetailResponse
import br.com.mobile.marvelcharacters.data.network.api.MarvelCharactersApi
import br.com.mobile.marvelcharacters.data.service.DataResult
import br.com.mobile.marvelcharacters.data.utils.Logger
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.unmockkAll
import java.io.IOException
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Response

class MarvelCharactersDetailDataSourceTest {

    @MockK
    private lateinit var mockApi: MarvelCharactersApi

    private lateinit var dataSource: MarvelCharactersDetailDataSource

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        dataSource = MarvelCharactersDetailDataSource(mockApi)
        val mockLogger = mockk<Logger>(relaxed = true)
        every { mockLogger.logError(any(), any()) } just runs
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test getMarvelCharacterDetail success`() = runBlocking {
        val mockMarvelCharactersDetailResponse = mockk<MarvelCharactersDetailResponse>()

        val mockResponse = MarvelApiDataResponse(mockMarvelCharactersDetailResponse)
        coEvery { mockApi.getCharactersDetail() } returns Response.success(mockResponse)

        val result = runBlocking { dataSource.getMarvelCharacterDetail() }

        Assertions.assertEquals(DataResult.Success(mockResponse), result)
    }

    @Test
    fun `test getMarvelCharacterDetail api error failure`() = runBlocking {
        val mockResponse: Response<MarvelApiDataResponse> = mockk()

        every { mockResponse.isSuccessful } returns false
        every { mockResponse.message() } returns "api error"
        every { mockResponse.code() } returns 404

        coEvery { mockApi.getCharactersDetail() } returns mockResponse

        val result = runBlocking { dataSource.getMarvelCharacterDetail() }

        val expectedError = DataResult.NetworkError("API call failed: api error", 404)
        Assertions.assertEquals(expectedError, result)
    }

    @Test
    fun `test getMarvelCharacterDetail empty response`() {
        val mockResponse: Response<MarvelApiDataResponse> = mockk()

        every { mockResponse.isSuccessful } returns true
        every { mockResponse.body() } returns null
        every { mockResponse.code() } returns 204
        coEvery { mockApi.getCharactersDetail() } returns mockResponse

        val result = runBlocking { dataSource.getMarvelCharacterDetail() }

        val expectedError = DataResult.GenericError("Empty response body", 204, null)
        Assertions.assertEquals(expectedError, result)
    }

    @Test
    fun `test getMarvelCharacterDetail IOException`() {
        coEvery { mockApi.getCharactersDetail() } throws IOException("Network error")

        val result = runBlocking { dataSource.getMarvelCharacterDetail() }

        val expectedError = DataResult.NetworkError("Network error: Network error")
        Assertions.assertEquals(expectedError, result)
    }

    @Test
    fun `test getMarvelCharacterDetail generic exception`() {
        coEvery { mockApi.getCharactersDetail() } throws Exception("Unexpected error")

        val result = runBlocking { dataSource.getMarvelCharacterDetail() }

        val expectedError = DataResult.NetworkError("Unexpected error: Unexpected error")
        Assertions.assertEquals(expectedError, result)
    }
}
