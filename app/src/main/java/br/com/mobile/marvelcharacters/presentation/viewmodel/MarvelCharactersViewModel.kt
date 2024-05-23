package br.com.mobile.marvelcharacters.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mobile.marvelcharacters.domain.model.onGenericError
import br.com.mobile.marvelcharacters.domain.model.onNetworkError
import br.com.mobile.marvelcharacters.domain.model.onSuccess
import br.com.mobile.marvelcharacters.domain.usecase.GetMarvelCharacterUseCase
import br.com.mobile.marvelcharacters.presentation.ui.state.MarvelCharactersViewState
import kotlinx.coroutines.launch

class MarvelCharactersViewModel(
    private val getMarvelCharacterUseCaseImpl: GetMarvelCharacterUseCase,
) : ViewModel() {
    private val _viewState = MutableLiveData<MarvelCharactersViewState>()
    val viewState: LiveData<MarvelCharactersViewState> = _viewState

    fun getMarvelCharactersDetails() {
        _viewState.value = MarvelCharactersViewState.Loading

        viewModelScope.launch {
            val result = getMarvelCharacterUseCaseImpl()

            result.onSuccess { marvelCharacters ->
                _viewState.value = MarvelCharactersViewState.Success(marvelCharacters)
            }
            result.onGenericError {
                _viewState.value = MarvelCharactersViewState.Error
            }
            result.onNetworkError {
                _viewState.value = MarvelCharactersViewState.Error
            }
        }
    }
}
