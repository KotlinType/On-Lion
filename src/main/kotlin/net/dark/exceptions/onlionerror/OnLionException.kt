package net.dark.exceptions.onlionerror

class OnLionException(body: OnLionErrorData): Throwable() {
    val onLionErrorBody: OnLionErrorData = body
}

@Throws(OnLionException::class)
fun OnLionErrorData.nextOrThrowOnLionException() {
    if (errors.isNotEmpty())
        throw OnLionException(
            OnLionErrorData(
                httpStatusCode = httpStatusCode,
                message = message,
                errors = errors
            )
        )
}
