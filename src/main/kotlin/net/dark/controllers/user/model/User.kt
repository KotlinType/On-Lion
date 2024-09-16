package net.dark.controllers.user.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long? = null,
    val email: String,
    val password: String,
    var isActivated: Boolean = false,
    val activationLink: String = ""
)
