package net.dark.services.user.model

import kotlinx.serialization.Serializable
import net.dark.dtos.UserDTO

@Serializable
data class RefreshTokenReceiveRemote(
    val refreshToken: String
)

@Serializable
data class RefreshTokenResponseRemote(
    val userDTO: UserDTO,
    val refreshToken: String,
    val accessToken: String,
)
