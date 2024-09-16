package net.dark.services.user.model

import kotlinx.serialization.Serializable
import net.dark.dtos.UserDTO

@Serializable
data class RegisterReceiverRemote(
    val email: String,
    val password: String,
    val isActivated: Boolean = false,
    val activationLink: String = ""
)

@Serializable
data class RegistrationResponseRemote(
    val user: UserDTO,
    val accessToken: String,
    val refreshToken: String,
)
