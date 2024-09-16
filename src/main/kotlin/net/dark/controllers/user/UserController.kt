package net.dark.controllers.user

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondRedirect
import net.dark.exceptions.onlionerror.nextOrThrowOnLionException
import net.dark.exceptions.onlionerror.unexpectedError
import net.dark.meddleware.middleware
import net.dark.services.user.UserService
import net.dark.services.user.model.LoginReceiverRemote
import net.dark.services.user.model.LogoutReceiverRemote
import net.dark.services.user.model.RefreshTokenReceiveRemote
import net.dark.services.user.model.RegisterReceiverRemote
import net.dark.utils.EnvConfig

object UserController {

    suspend fun registration(call: ApplicationCall) {
        try {
            val receiver = call.receive<RegisterReceiverRemote>()
            val response = UserService.registration(receiver.email, receiver.password)
            call.respond(response)
        } catch (e: Throwable) {
            middleware(e, call)
        }
    }

    suspend fun login(call: ApplicationCall) {
        try {
            val receiver = call.receive<LoginReceiverRemote>()
            val response = UserService.login(receiver.email, receiver.password)
            call.respond(response)
        } catch (e: Throwable) {
            middleware(e, call)
        }
    }

    suspend fun logout(call: ApplicationCall) {
        try {
            val receiver = call.receive<LogoutReceiverRemote>()
            UserService.logout(receiver.refreshToken)
            call.respond(HttpStatusCode.OK)
        } catch (e: Throwable) {
            middleware(e, call)
        }
    }

    suspend fun activate(call: ApplicationCall) {
        try {
            UserService.activate(call.parameters["link"] ?: "")
            call.respondRedirect(EnvConfig.clientURL)

        } catch (e: Throwable) {
            middleware(e, call)
        }
    }

    suspend fun refreshToken(call: ApplicationCall) {
        try {
            val receiver = call.receive<RefreshTokenReceiveRemote>()
            UserService.refreshToken(receiver.refreshToken)
            call.respond(HttpStatusCode.OK)
        } catch (e: Throwable) {
            middleware(e, call)
        }
    }
}
