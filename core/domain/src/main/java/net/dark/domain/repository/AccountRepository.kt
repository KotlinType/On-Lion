package net.dark.domain.repository

import kotlinx.coroutines.flow.Flow
import net.dark.domain.model.LoginRequest
import net.dark.domain.model.LoginResponse
import net.dark.domain.model.LogoutRequest
import net.dark.domain.model.RefreshTokenRequest
import net.dark.domain.model.RefreshTokenResponse
import net.dark.domain.model.RegistrationRequest
import net.dark.domain.model.RegistrationResponse
import net.dark.domain.utils.NetworkResult

interface AccountRepository {
    fun registration(data: RegistrationRequest): Flow<NetworkResult<RegistrationResponse>>

    fun login(data: LoginRequest): Flow<NetworkResult<LoginResponse>>

    fun logout(data: LogoutRequest)

    fun refreshToken(data: RefreshTokenRequest): Flow<NetworkResult<RefreshTokenResponse>>
}
