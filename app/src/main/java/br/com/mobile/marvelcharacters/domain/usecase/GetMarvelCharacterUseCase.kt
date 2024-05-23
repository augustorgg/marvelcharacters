package br.com.mobile.marvelcharacters.domain.usecase

import br.com.mobile.marvelcharacters.domain.model.CharacterResult
import br.com.mobile.marvelcharacters.domain.model.Result
import br.com.mobile.marvelcharacters.domain.repository.MarvelCharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetMarvelCharacterUseCase(
    private val repository: MarvelCharacterRepository,
    private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(): Result<List<CharacterResult>?> {
        return withContext(ioDispatcher) {
            repository.getMarvelCharactersDetail()
        }
    }
}
