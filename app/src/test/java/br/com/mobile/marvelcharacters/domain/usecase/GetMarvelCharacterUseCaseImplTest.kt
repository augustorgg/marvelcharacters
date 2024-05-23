package br.com.mobile.marvelcharacters.domain.usecase

import br.com.mobile.marvelcharacters.domain.model.CharacterResult
import br.com.mobile.marvelcharacters.domain.model.Result
import br.com.mobile.marvelcharacters.domain.repository.MarvelCharacterRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetMarvelCharacterUseCaseImplTest {
    private lateinit var useCase: GetMarvelCharacterUseCase
    private lateinit var repository: MarvelCharacterRepository
    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        repository = mockk()
        useCase = GetMarvelCharacterUseCaseImpl(repository, testDispatcher)
    }

    @Test
    fun `invoke returns success result`() =
        runTest(testScheduler) {
            val mockCharacterResultList = listOf(mockk<CharacterResult>())
            val expectedResult = Result.Success(mockCharacterResultList)

            coEvery { repository.getMarvelCharactersDetail() } returns expectedResult

            val result = useCase()

            assertEquals(expectedResult, result)
            coVerify { repository.getMarvelCharactersDetail() }
        }

    @Test
    fun `invoke returns network error result`() =
        runTest(testScheduler) {
            val errorMessage = "Network error"
            val statusCode = 404
            val expectedResult = Result.NetworkError(errorMessage, statusCode)

            coEvery { repository.getMarvelCharactersDetail() } returns expectedResult

            val result = useCase()

            assertEquals(expectedResult, result)
            coVerify { repository.getMarvelCharactersDetail() }
        }

    @Test
    fun `invoke returns generic error result`() =
        runTest(testScheduler) {
            val exception = Exception("Generic error")
            val expectedResult = Result.GenericError(exception)

            coEvery { repository.getMarvelCharactersDetail() } returns expectedResult

            val result = useCase()

            assertEquals(expectedResult, result)
            coVerify { repository.getMarvelCharactersDetail() }
        }
}
