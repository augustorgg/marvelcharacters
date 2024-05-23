package br.com.mobile.marvelcharacters.data.network.api

import br.com.mobile.marvelcharacters.data.model.MarvelApiDataResponse
import retrofit2.Response
import retrofit2.http.GET

interface MarvelCharactersApi {
    @GET("characters")
    suspend fun getCharactersDetail(): Response<MarvelApiDataResponse>
}
