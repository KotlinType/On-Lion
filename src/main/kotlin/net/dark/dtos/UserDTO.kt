package net.dark.dtos

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val id: Long,
    val email: String,
    val isActivated: Boolean = false,
)
