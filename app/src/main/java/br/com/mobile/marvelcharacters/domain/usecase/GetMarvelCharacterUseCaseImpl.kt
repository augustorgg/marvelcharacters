package br.com.mobile.marvelcharacters.domain.usecase

import br.com.mobile.marvelcharacters.domain.model.CharacterResult
import br.com.mobile.marvelcharacters.domain.model.Result
import br.com.mobile.marvelcharacters.domain.repository.MarvelCharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetMarvelCharacterUseCaseImpl(
    private val repository: MarvelCharacterRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : GetMarvelCharacterUseCase {
    override suspend operator fun invoke(): Result<List<CharacterResult>?> {
        return withContext(ioDispatcher) {
            repository.getMarvelCharactersDetail()
        }
    }
}
