package net.dark.exceptions.onlionerror.model

abstract class OnLionError {
    companion object {
        fun alreadyRegistered(email: String) = "Пользоватеь с почтой $email уже существует"
    }

    enum class Error(val value: String) {
        INCORRECT_EMAIL("Некорректный email."),

        INCORRECT_PASSWORD_NUMBER("Пароль должен содиржать хотя по крайней мере одну цифру."),
        INCORRECT_PASSWORD_UPPER_CASE("Пароль должен содержать символы как в верхнем, так и в нижнем регистре."),
        INCORRECT_PASSWORD_LENGTH("Пароль должен состоять как минимум из 6 символов."),

        UNKNOWN_ERROR("Непредвиденная ошибка"),

        USER_IS_NOT_LOGGED("Пользователь не авторизирован"),

        INVALID_LINK("Невалидная ссылка"),

        INCORRECT_EMAIL_OR_PASSWORD("Неверный email или пароль"),
    }
}
