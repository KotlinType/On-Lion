package net.dark.domain.model

data class RefreshTokenRequest(
    val refreshToken: String
)

data class RefreshTokenResponse(
    val refreshToken: String,
    val accessToken: String
)
