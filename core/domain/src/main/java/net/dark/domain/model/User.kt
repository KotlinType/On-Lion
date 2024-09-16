package net.dark.domain.model

data class User(
    val id: Long,
    val email: String,
    val isActivated: Boolean,
)
