package br.com.mobile.marvelcharacters.presentation.viewmodel

import androidx.lifecycle.Observer
import br.com.mobile.marvelcharacters.domain.model.CharacterResult
import br.com.mobile.marvelcharacters.domain.model.Result
import br.com.mobile.marvelcharacters.domain.usecase.GetMarvelCharacterUseCase
import br.com.mobile.marvelcharacters.presentation.ui.state.MarvelCharactersViewState
import br.com.mobile.marvelcharacters.presentation.utils.InstantTaskExecutorRuleForJUnit5
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorRuleForJUnit5::class)
class MarvelCharactersViewModelTest {
    private lateinit var useCase: GetMarvelCharacterUseCase

    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)
    private lateinit var viewModel: MarvelCharactersViewModel
    private lateinit var viewStateObserver: Observer<MarvelCharactersViewState>

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        MockKAnnotations.init()
        Dispatchers.setMain(testDispatcher)
        useCase = mockk<GetMarvelCharacterUseCase>()
        viewModel = MarvelCharactersViewModel(useCase)

        viewStateObserver = mockk(relaxed = true)
        viewModel.viewState.observeForever(viewStateObserver)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMarvelCharactersDetails success`() =
        runTest(testScheduler) {
            val mockCharacterList = listOf<CharacterResult>()
            val expectedResult = Result.Success(mockCharacterList)
            coEvery { useCase() } returns expectedResult

            viewModel.getMarvelCharactersDetails()
            testScheduler.apply {
                advanceTimeBy(1000)
                runCurrent()
            }

            val expectedViewState = MarvelCharactersViewState.Success(mockCharacterList)
            Assertions.assertEquals(expectedViewState, viewModel.viewState.value)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMarvelCharactersDetails network error`() =
        runTest(testScheduler) {
            val errorMessage = "Network error"
            val statusCode = 404
            val expectedResult = Result.NetworkError(errorMessage, statusCode)
            coEvery { useCase() } returns expectedResult

            viewModel.getMarvelCharactersDetails()
            testScheduler.apply {
                advanceTimeBy(1000)
                runCurrent()
            }

            val expectedViewState = MarvelCharactersViewState.Error
            Assertions.assertEquals(expectedViewState, viewModel.viewState.value)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMarvelCharactersDetails generic error`() =
        runTest(testScheduler) {
            val exception = Exception("Generic error")
            val expectedResult = Result.GenericError(exception)
            coEvery { useCase() } returns expectedResult

            viewModel.getMarvelCharactersDetails()
            testScheduler.apply {
                advanceTimeBy(1000)
                runCurrent()
            }

            val expectedViewState = MarvelCharactersViewState.Error
            Assertions.assertEquals(expectedViewState, viewModel.viewState.value)
        }
}
