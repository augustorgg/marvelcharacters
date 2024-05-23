package br.com.mobile.marvelcharacters.domain.model

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()

    data class NetworkError(val message: String = "Error", val httpStatus: Int = 500) : Result<Nothing>()

    data class GenericError(val exception: Exception?) : Result<Nothing>()
}
