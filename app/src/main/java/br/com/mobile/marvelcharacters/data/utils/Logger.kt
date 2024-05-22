package br.com.mobile.marvelcharacters.data.utils

import android.util.Log

object Logger {
    private const val TAG = "MarvelCharactersApp"

    fun logError(message: String, throwable: Throwable? = null) {
        Log.e(TAG, message, throwable)
    }

    fun logDebug(message: String) {
        Log.d(TAG, message)
    }
}
