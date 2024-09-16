package net.dark.domain.model

data class RegistrationRequest(
    val email: String,
    val password: String
)

data class RegistrationResponse(
    val user: User,
    val refreshToke: String,
    val accessToken: String
)
