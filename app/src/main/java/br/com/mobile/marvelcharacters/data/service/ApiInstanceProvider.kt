package br.com.mobile.marvelcharacters.data.service

import br.com.mobile.marvelcharacters.data.network.AuthInterceptor
import br.com.mobile.marvelcharacters.data.network.GzipInterceptor
import br.com.mobile.marvelcharacters.data.network.MarvelCharactersApi
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstanceProvider {
    private const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
    private const val TIMEOUT_SECONDS = 30L

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(AuthInterceptor())
        .addNetworkInterceptor(GzipInterceptor())
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("Accept-Encoding", "gzip")
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val marvelCharactersApi: MarvelCharactersApi = retrofit.create(MarvelCharactersApi::class.java)
}
