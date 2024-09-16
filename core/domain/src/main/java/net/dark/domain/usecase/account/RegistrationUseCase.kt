package net.dark.domain.usecase.account

import kotlinx.coroutines.flow.Flow
import net.dark.domain.model.RegistrationRequest
import net.dark.domain.model.RegistrationResponse
import net.dark.domain.repository.AccountRepository
import net.dark.domain.utils.NetworkResult

class RegistrationUseCase(
    private val userRepository: AccountRepository
) {
    fun invoke(request: RegistrationRequest): Flow<NetworkResult<RegistrationResponse>> =
        userRepository.registration(request)
}