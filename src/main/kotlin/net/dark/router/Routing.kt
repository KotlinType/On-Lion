package net.dark.router

import io.ktor.server.application.*
import net.dark.router.routes.configureLoginRouting
import net.dark.router.routes.configureRegisterRouting
import net.dark.router.routes.configureSettingsRouting

fun Application.configureRouting() {
    configureLoginRouting()
    configureRegisterRouting()
    configureSettingsRouting()
}
