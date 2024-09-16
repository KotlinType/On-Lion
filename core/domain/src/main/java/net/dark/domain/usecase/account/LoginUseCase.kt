package net.dark.domain.usecase.account

import kotlinx.coroutines.flow.Flow
import net.dark.domain.model.LoginRequest
import net.dark.domain.model.LoginResponse
import net.dark.domain.repository.AccountRepository
import net.dark.domain.utils.NetworkResult

class LoginUseCase(
    private val userRepository: AccountRepository
) {
    fun invoke(request: LoginRequest): Flow<NetworkResult<LoginResponse>> =
        userRepository.login(request)
}