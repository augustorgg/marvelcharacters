package br.com.mobile.marvelcharacters.domain.model

inline fun <T : Any> Result<T?>.onSuccess(action: (T) -> Unit): Result<T?> {
    if (this is Result.Success) {
        this.value?.let { action(it) }
    }
    return this
}

inline fun <T : Any> Result<T?>.onNetworkError(action: (Result.NetworkError) -> Unit): Result<T?> {
    if (this is Result.NetworkError) {
        action(this)
    }
    return this
}

inline fun <T : Any> Result<T?>.onGenericError(action: (Result.GenericError) -> Unit): Result<T?> {
    if (this is Result.GenericError) {
        action(this)
    }
    return this
}
