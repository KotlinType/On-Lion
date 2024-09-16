package net.dark.utils

import io.github.cdimascio.dotenv.dotenv

object EnvConfig {
    private val env = dotenv { ignoreIfMissing = true }

    val serverPort: Int by lazy { env["SERVER_PORT"].toInt() }
    val serverHost: String by lazy { env["SERVER_HOST"] ?: "" }
    val serveURL: String by lazy { env["SERVER_URL"] ?: "" }

    val clientURL: String by lazy { env["CLIENT_URL"] ?: "" }

    val dbUrl: String by lazy { env["DB_URL"] ?: "" }
    val dbDriver: String by lazy { env["DB_DRIVER"] ?: "" }
    val dbPassword: String by lazy { env["DB_PASSWORD"] ?: "" }
    val dbUser: String by lazy { env["DB_USER"] ?: "" }

    val smtpPort: Int by lazy { env["SMTP_PORT"].toInt() }
    val smtpHost: String by lazy { env["SMTP_HOST"] ?: "" }
    val smtpUser: String by lazy { env["SMTP_USER"] ?: "" }
    val smtpPassword: String by lazy { env["SMTP_PASSWORD"] ?: "" }

    val jwtAccessSecret: String by lazy { env["JWT_ACCESS_SECRET"] ?: "" }
    val jwtRefreshSecret: String by lazy { env["JWT_REFRESH_SECRET"] ?: "" }

    val bCryptGenSalt: Int by lazy { env["BCRYPT_GEN_SALT"].toInt() }
}
