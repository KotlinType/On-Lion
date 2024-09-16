package net.dark.dtos

import kotlinx.serialization.Serializable

@Serializable
data class OnLionErrorDTO(
    val message: String,
    val errors: List<String> = listOf()
)
