package com.ktacademy.foodie.data.network

data class AppResponse <T>(
    val body: T? = null,
    val status: String
)

sealed class AppResource<T>(
    val data: T? = null,
    val status: String? = null
) {
    class Success<T>(body: T, status: String): AppResource<T>(body, status)
    class Loading<T> : AppResource<T>()
    class Error<T>(status: String): AppResource<T>(status = status)
}
