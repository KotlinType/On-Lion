package net.dark.meddleware

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import net.dark.dtos.OnLionErrorDTO
import net.dark.exceptions.onlionerror.OnLionException
import net.dark.exceptions.onlionerror.model.OnLionError

suspend fun middleware(error: Throwable, call: ApplicationCall) {
    println(error)
    when (error) {
        is OnLionException -> {
            call.respond(
                status = error.onLionErrorBody.httpStatusCode,
                message = OnLionErrorDTO(error.onLionErrorBody.message, error.onLionErrorBody.errors)
            )
        }

        else -> {
            call.respond(
                status = HttpStatusCode.InternalServerError,
                message = OnLionErrorDTO(OnLionError.Error.UNKNOWN_ERROR.value)
            )
        }
    }
}
