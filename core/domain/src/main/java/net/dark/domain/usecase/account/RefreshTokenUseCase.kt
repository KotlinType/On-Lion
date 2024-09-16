package net.dark.domain.usecase.account

import kotlinx.coroutines.flow.Flow
import net.dark.domain.model.RefreshTokenRequest
import net.dark.domain.model.RefreshTokenResponse
import net.dark.domain.repository.AccountRepository
import net.dark.domain.utils.NetworkResult

class RefreshTokenUseCase(
    private val userRepository: AccountRepository
) {
    fun invoke(request: RefreshTokenRequest): Flow<NetworkResult<RefreshTokenResponse>> =
        userRepository.refreshToken(request)
}