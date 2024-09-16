package net.dark

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import net.dark.router.configureRouting
import net.dark.plugins.configureSerialization
import net.dark.plugins.configureSockets
import net.dark.utils.EnvConfig
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect(
        url = EnvConfig.dbUrl,
        driver = EnvConfig.dbDriver,
        user = EnvConfig.dbUser,
        password = EnvConfig.dbPassword
        )

    embeddedServer(CIO, port = EnvConfig.serverPort, host = EnvConfig.serverHost, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSockets()
    configureRouting()
    configureSerialization()
}
