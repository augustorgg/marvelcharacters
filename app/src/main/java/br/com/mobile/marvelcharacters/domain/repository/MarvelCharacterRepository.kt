package br.com.mobile.marvelcharacters.domain.repository

import br.com.mobile.marvelcharacters.domain.model.CharacterResult
import br.com.mobile.marvelcharacters.domain.model.Result

interface MarvelCharacterRepository {
    suspend fun getMarvelCharactersDetail(): Result<List<CharacterResult>?>
}
