package br.com.mobile.marvelcharacters.data.network.datasource

import br.com.mobile.marvelcharacters.data.model.MarvelApiDataResponse
import br.com.mobile.marvelcharacters.data.network.api.MarvelCharactersApi
import br.com.mobile.marvelcharacters.data.service.DataResult
import br.com.mobile.marvelcharacters.data.utils.Logger
import java.io.IOException
import retrofit2.Response

class MarvelCharactersDetailDataSource(private val api: MarvelCharactersApi) {
    suspend fun getMarvelCharacterDetail(): DataResult<MarvelApiDataResponse> {
        return handleApiCall { api.getCharactersDetail() }
    }

    private suspend fun <T> handleApiCall(call: suspend () -> Response<T>): DataResult<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    DataResult.Success(body)
                } else {
                    DataResult.GenericError("Empty response body", response.code())
                }
            } else {
                val errorMessage = "API call failed: ${response.message()}"
                Logger.logError(errorMessage)
                DataResult.NetworkError(errorMessage, response.code())
            }
        } catch (e: IOException) {
            val errorMessage = "Network error: ${e.message}"
            Logger.logError(errorMessage)
            DataResult.NetworkError(errorMessage)
        } catch (e: Exception) {
            val errorMessage = "Unexpected error: ${e.message}"
            Logger.logError(errorMessage)
            DataResult.NetworkError(errorMessage)
        }
    }
}
