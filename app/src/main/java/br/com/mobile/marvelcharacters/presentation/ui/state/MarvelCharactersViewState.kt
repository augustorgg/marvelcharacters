package br.com.mobile.marvelcharacters.presentation.ui.state

import br.com.mobile.marvelcharacters.domain.model.CharacterResult

sealed class MarvelCharactersViewState {
    data object Loading : MarvelCharactersViewState()
    data class Success(val marvelCharacters: List<CharacterResult>?) :
        MarvelCharactersViewState()

    data class Error(val message: String) : MarvelCharactersViewState()
}
