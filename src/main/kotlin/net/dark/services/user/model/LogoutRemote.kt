package net.dark.services.user.model

import kotlinx.serialization.Serializable

@Serializable
data class LogoutReceiverRemote(
    val refreshToken: String
)
