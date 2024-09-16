package net.dark.services

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import net.dark.dtos.TokenDTO
import net.dark.database.tokens.Tokens
import net.dark.dtos.UserDTO
import net.dark.utils.EnvConfig
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.Calendar
import java.util.Date

data class Token(
    val accessToken: String,
    val refreshToken: String,
)

object TokenService {
    fun generateTokens(payload: String): Token {
        val accessToken = generateJWT(
            payload = payload,
            secretKey = EnvConfig.jwtAccessSecret,
            calendarUnit = Calendar.SECOND,
            30
        )
        val refreshToken = generateJWT(
            payload = payload,
            secretKey = EnvConfig.jwtRefreshSecret,
            calendarUnit = Calendar.DAY_OF_MONTH,
            30
        )
        return Token(accessToken, refreshToken)
    }

    fun validateAccessToken(token: String): UserDTO? {
        return try {
            val verifier = JWT.require(Algorithm.HMAC256(EnvConfig.jwtAccessSecret)).build()
            val decodedJwt = verifier.verify(token)
            Json.decodeFromString<UserDTO>(decodedJwt.getClaim("payload").asString())
        } catch (e: Exception) {
            null
        }
    }

    fun validateRefreshToken(token: String): UserDTO? {
        return try {
            val verifier = JWT.require(Algorithm.HMAC256(EnvConfig.jwtRefreshSecret)).build()
            val decodedJwt = verifier.verify(token)
            Json.decodeFromString<UserDTO>(decodedJwt.getClaim("payload").asString())
        } catch (e: Exception) {
            null
        }
    }

    fun saveToken(userId: Long, refreshToken: String) {
        transaction {
            val tokenData = Tokens.check(userId)
            if (tokenData) Tokens.update(TokenDTO(userId, refreshToken))
            else Tokens.insert(TokenDTO(userId, refreshToken))
        }
    }

    private fun generateJWT(
        payload: String,
        secretKey: String,
        calendarUnit: Int,
        time: Int
    ): String {
        val date = Calendar.getInstance()
        date.time = Date()
        date.add(calendarUnit, time)
        return JWT.create()
            .withClaim("payload", payload)
            .withExpiresAt(date.time)
            .sign(Algorithm.HMAC256(secretKey))
    }
}
