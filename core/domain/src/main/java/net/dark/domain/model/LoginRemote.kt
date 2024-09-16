package net.dark.domain.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val user: User,
    val refreshToken: String,
    val accessToken: String
)
