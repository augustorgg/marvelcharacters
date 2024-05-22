package br.com.mobile.marvelcharacters.data.service

sealed class DataResult<out T> {
    data class Success<out T>(val value: T) : DataResult<T>()
    data class NetworkError(
        val message: String = "Error",
        val httpStatus: Int = 500
    ) : DataResult<Nothing>()

    data class GenericError(
        val exception: Exception
    ) : DataResult<Nothing>()
}

inline fun <T : Any> DataResult<T>.onSuccess(action: (T) -> Unit): DataResult<T> {
    if (this is DataResult.Success) {
        action(this.value)
    }
    return this
}

inline fun <T : Any> DataResult<T>.onError(action: (DataResult.NetworkError) -> Unit): DataResult<T> {
    if (this is DataResult.NetworkError) {
        action(this)
    }
    return this
}