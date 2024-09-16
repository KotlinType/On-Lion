package net.dark.router.routes

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import net.dark.controllers.user.UserController

fun Application.configureLoginRouting() {

    routing {
        route("/api/v1") {
            post("/login") {
                UserController.login(call)
            }
            post("/logout") {
                UserController.logout(call)
            }
            post("/refresh") {
                UserController.refreshToken(call)
            }
        }
    }
}
