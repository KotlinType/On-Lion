package net.dark.exceptions.onlionerror

import io.ktor.http.HttpStatusCode
import net.dark.exceptions.onlionerror.model.OnLionError
import net.dark.exceptions.onlionerror.model.TypeOnLionError

fun String.emailExist() : OnLionErrorData =
    OnLionErrorData(
        message = TypeOnLionError.ERROR_REGISTRATION.value,
        httpStatusCode = HttpStatusCode.BadRequest,
        errors = listOf(OnLionError.alreadyRegistered(this))
    )

fun String.validPassword() : OnLionErrorData {
    val errors = arrayListOf<String>()

    val regexCase = Regex("^(?=.*[a-z])(?=.*[A-Z]).+$")
    val regexNumberCase = Regex("^(?=.*[0-9]).+$")

    if (this.length < 6) errors.add(OnLionError.Error.INCORRECT_PASSWORD_LENGTH.value)
    if (!this.matches(regexCase)) errors.add(OnLionError.Error.INCORRECT_PASSWORD_UPPER_CASE.value)
    if (!this.matches(regexNumberCase)) errors.add(OnLionError.Error.INCORRECT_PASSWORD_NUMBER.value)

    return OnLionErrorData(
        message = TypeOnLionError.ERROR_VALID_PASSWORD.value,
        httpStatusCode = HttpStatusCode.BadRequest,
        errors = errors
    )
}

fun String.validEmail() : OnLionErrorData {
    val errors = arrayListOf<String>()

    val regex = Regex("""^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""")

    if (!this.matches(regex)) errors.add(OnLionError.Error.INCORRECT_EMAIL.value)

    return OnLionErrorData(
        message = TypeOnLionError.ERROR_VALID_EMAIL.value,
        httpStatusCode = HttpStatusCode.BadRequest,
        errors = errors
    )
}

fun invalidLink() : OnLionErrorData =
    OnLionErrorData(
        message = TypeOnLionError.ERROR_ACTIVATION_ACCOUNT.value,
        httpStatusCode = HttpStatusCode.InternalServerError,
        errors = listOf(OnLionError.Error.INVALID_LINK.value)
    )

fun incorrectEmailOrPassword() : OnLionErrorData =
    OnLionErrorData(
        message = TypeOnLionError.ERROR_LOGIN.value,
        httpStatusCode = HttpStatusCode.BadRequest,
        errors = listOf(OnLionError.Error.INCORRECT_EMAIL_OR_PASSWORD.value)
    )

fun unexpectedError() : OnLionErrorData =
    OnLionErrorData(
        message = TypeOnLionError.ERROR_SERVER.value,
        httpStatusCode = HttpStatusCode.InternalServerError,
        errors = listOf(OnLionError.Error.UNKNOWN_ERROR.value)
    )


fun unauthorizedError() : OnLionErrorData =
    OnLionErrorData(
        message = TypeOnLionError.ERROR_EXCEEDING_ACCESS_RIGHTS.value,
        httpStatusCode = HttpStatusCode.Unauthorized,
        errors = listOf(OnLionError.Error.USER_IS_NOT_LOGGED.value)
    )
