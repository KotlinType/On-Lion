package net.dark.exceptions.onlionerror.model

enum class TypeOnLionError(val value: String) {
    ERROR_REGISTRATION("Ошибка регистрации"),
    ERROR_EXCEEDING_ACCESS_RIGHTS("Ошибка получения доступа"),
    ERROR_LOGIN("Ошибка входа"),
    ERROR_VALID_EMAIL("Ошибка валидации email"),
    ERROR_VALID_PASSWORD("Ошибка валидации пароля"),
    ERROR_ACTIVATION_ACCOUNT("Ошибка активации аккаунта"),
    ERROR_SERVER("Ошибка сервера"),
}
