package net.dark.exceptions.onlionerror

import io.ktor.http.HttpStatusCode

data class OnLionErrorData(
    val httpStatusCode: HttpStatusCode,
    val message: String,
    val errors: List<String> = listOf()
)
