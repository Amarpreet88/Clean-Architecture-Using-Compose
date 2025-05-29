package com.amar.cleanUsingCompose.domain.common

sealed class ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Failure(
        val error: String,
        val message: String
    ) : ResultState<Nothing>()

    object Loading : ResultState<Nothing>()
}