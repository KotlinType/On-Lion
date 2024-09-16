package net.dark.domain.usecase.account

import net.dark.domain.model.LogoutRequest
import net.dark.domain.repository.AccountRepository

class LogoutUseCase(
    private val userRepository: AccountRepository
) {
    fun invoke(request: LogoutRequest) = userRepository.logout(request)
}