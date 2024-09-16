package net.dark.services.user

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.dark.dtos.UserDTO
import net.dark.database.uses.Users
import net.dark.services.user.model.RegistrationResponseRemote
import net.dark.services.MailService
import net.dark.services.TokenService
import net.dark.controllers.user.model.User
import net.dark.database.tokens.Tokens
import net.dark.exceptions.onlionerror.emailExist
import net.dark.exceptions.onlionerror.incorrectEmailOrPassword
import net.dark.exceptions.onlionerror.invalidLink
import net.dark.exceptions.onlionerror.nextOrThrowOnLionException
import net.dark.exceptions.onlionerror.unauthorizedError
import net.dark.exceptions.onlionerror.unexpectedError
import net.dark.utils.EnvConfig
import net.dark.exceptions.onlionerror.validEmail
import net.dark.exceptions.onlionerror.validPassword
import net.dark.services.user.model.LoginResponseRemote
import net.dark.services.user.model.RefreshTokenResponseRemote
import org.mindrot.jbcrypt.BCrypt
import java.util.UUID

object UserService {

    @Throws(Throwable::class)
    fun registration(email: String, password: String): RegistrationResponseRemote {

        email.validEmail().nextOrThrowOnLionException()
        password.validPassword().nextOrThrowOnLionException()

        if (Users.fetchUserByLogin(email) != null) email.emailExist().nextOrThrowOnLionException()

        val hashPassword = BCrypt.hashpw(password, BCrypt.gensalt(EnvConfig.bCryptGenSalt))
        val activationLink = UUID.randomUUID().toString()
        val userId = Users.insert(
            User(email = email, password = hashPassword, activationLink = activationLink)
        )

        MailService.sendActivationMain(
            to = email, link = "${EnvConfig.serveURL}/api/v1/activate/$activationLink"
        )

        val userDto = UserDTO(id = userId, email = email)
        val tokens = TokenService.generateTokens(userDto.toString())

        TokenService.saveToken(userId, tokens.refreshToken)

        return RegistrationResponseRemote(
            user = userDto,
            accessToken = tokens.accessToken,
            refreshToken = tokens.refreshToken
        )
    }

    @Throws(Throwable::class)
    fun login(email: String, password: String): LoginResponseRemote {
        email.validEmail().nextOrThrowOnLionException()
        password.validPassword().nextOrThrowOnLionException()

        val user = Users.fetchUserByLogin(email)

        when (user) {
            null -> incorrectEmailOrPassword().nextOrThrowOnLionException()
            else -> {
                val checkPassword = BCrypt.checkpw(password, user.password)
                if (!checkPassword) incorrectEmailOrPassword().nextOrThrowOnLionException()
            }
        }

        val userDto = UserDTO(id = user!!.id!!, email = email, isActivated = user.isActivated)
        val tokens = TokenService.generateTokens(Json.encodeToString(userDto))

        TokenService.saveToken(user.id!!, tokens.refreshToken)

        return LoginResponseRemote(
            user = userDto,
            accessToken = tokens.accessToken,
            refreshToken = tokens.refreshToken
        )
    }

    @Throws(Throwable::class)
    fun logout(refreshToken: String) {
        val result = Tokens.delete(refreshToken)
        if (result == 0) unexpectedError().nextOrThrowOnLionException()
    }

    @Throws(Throwable::class)
    fun refreshToken(refreshToken: String): RefreshTokenResponseRemote {
        val token = Tokens.fetchTokenByRefreshToken(refreshToken)
        if (token == null) unauthorizedError().nextOrThrowOnLionException()

        val oldToken = TokenService.validateRefreshToken(refreshToken)
        if (oldToken == null) unauthorizedError().nextOrThrowOnLionException()

        val user = Users.fetchUserById(token!!.userId)
        if (user == null) unauthorizedError().nextOrThrowOnLionException()

        val userDto = UserDTO(token.userId, user!!.email, user.isActivated)
        val tokens = TokenService.generateTokens(userDto.toString())

        TokenService.saveToken(user.id!!, tokens.refreshToken)

        return RefreshTokenResponseRemote(
            userDTO = userDto,
            refreshToken = tokens.refreshToken,
            accessToken = tokens.accessToken
        )
    }

    fun activate(activationLink: String) {
        val user = Users.fetchUserByActivationLink(activationLink)?.apply { isActivated = true }
        if (user == null) invalidLink()
        else Users.update(user)
    }
}
