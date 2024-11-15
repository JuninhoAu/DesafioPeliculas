package com.juni.desafiopeliculas.common

sealed class ResultType<out T, out E> {
    data class Success<out T>(val data: T) : ResultType<T, Nothing>()
    data class Failure<out E>(val error: E) : ResultType<Nothing, E>()
}