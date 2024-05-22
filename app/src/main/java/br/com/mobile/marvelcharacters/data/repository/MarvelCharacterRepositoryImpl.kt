package br.com.mobile.marvelcharacters.data.repository

import br.com.mobile.marvelcharacters.data.network.datasource.MarvelCharactersDetailDataSource
import br.com.mobile.marvelcharacters.data.service.DataResult
import br.com.mobile.marvelcharacters.data.utils.Logger
import br.com.mobile.marvelcharacters.domain.mapper.MarvelCharactersDetailMapper
import br.com.mobile.marvelcharacters.domain.model.CharacterResult
import br.com.mobile.marvelcharacters.domain.model.Result
import br.com.mobile.marvelcharacters.domain.repository.MarvelCharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MarvelCharacterRepositoryImpl(
    private val dataSource: MarvelCharactersDetailDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : MarvelCharacterRepository {

    override suspend fun getMarvelCharactersDetail(): Result<List<CharacterResult>?> {
        return withContext(ioDispatcher) {
            try {
                when (val result = dataSource.getMarvelCharacterDetail()) {
                    is DataResult.Success -> {
                        val domainModelList =
                            MarvelCharactersDetailMapper.mapFromDataModel(result.value)
                        Result.Success(domainModelList.data?.results)
                    }

                    is DataResult.NetworkError -> {
                        Logger.logError(result.message)
                        Result.NetworkError(
                            result.message,
                            result.httpStatus
                        )

                    }

                    is DataResult.GenericError -> {
                        Logger.logError(result.exception.message, result.exception)
                        Result.GenericError(result.exception)
                    }
                }
            } catch (e: Exception) {
                Result.GenericError(e)
            }
        }
    }
}
