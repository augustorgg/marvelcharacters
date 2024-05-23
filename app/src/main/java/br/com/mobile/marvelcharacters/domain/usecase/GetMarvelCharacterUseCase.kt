package br.com.mobile.marvelcharacters.domain.usecase

import br.com.mobile.marvelcharacters.domain.model.CharacterResult
import br.com.mobile.marvelcharacters.domain.model.Result

interface GetMarvelCharacterUseCase {
    suspend operator fun invoke(): Result<List<CharacterResult>?>
}