package net.dark.router.routes

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import net.dark.controllers.user.UserController

fun Application.configureSettingsRouting() {

    routing {
        route("/api/v1") {
            get("/activate/{link}") {
                UserController.activate(call)
            }
        }
    }
}
