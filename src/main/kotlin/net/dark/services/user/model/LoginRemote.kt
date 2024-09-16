package net.dark.services.user.model

import kotlinx.serialization.Serializable
import net.dark.dtos.UserDTO

@Serializable
data class LoginReceiverRemote(
    val email: String,
    val password: String
)

@Serializable
data class LoginResponseRemote(
    val user: UserDTO,
    val accessToken: String,
    val refreshToken: String,
)
