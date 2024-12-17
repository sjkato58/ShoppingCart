package com.mtfuji.sakura.utilities

sealed class Outcome<out T> {
    data class Success<T>(val data: T) : Outcome<T>()
    data class Error(val exception: Throwable) : Outcome<Nothing>()
}