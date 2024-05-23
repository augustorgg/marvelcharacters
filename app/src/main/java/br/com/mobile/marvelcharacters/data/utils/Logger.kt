package br.com.mobile.marvelcharacters.data.utils

import android.util.Log

object Logger {
    private const val TAG = "MarvelCharactersApp"

    fun logError(
        message: String? = "A generic error occurred.",
        throwable: Throwable? = null,
    ) {
        Log.e(TAG, message, throwable)
    }
}
