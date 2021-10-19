package com.mahdi.ztd.infrastructure.domain.usecase

import com.mahdi.ztd.infrastructure.domain.domainutils.LiveDataResult
import com.mahdi.ztd.infrastructure.domain.model.requests.RegisterRequestModel
import com.mahdi.ztd.infrastructure.domain.model.requests.VerifyOtpRequestModel
import com.mahdi.ztd.infrastructure.domain.model.responses.ProfileResponseModel
import com.mahdi.ztd.infrastructure.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Mahdi ZTD
 *  on 2/18/2021 .
 *  Email: mz@imagineOn.de
 */

class GetLoginUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
		(String) -> Flow<LiveDataResult<Nothing>> {
	override fun invoke(cellphone: String): Flow<LiveDataResult<Nothing>> =
		authenticationRepository.login(cellphone)
}


class GetVerifyOtpUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
		(VerifyOtpRequestModel) -> Flow<LiveDataResult<ProfileResponseModel>> {
	override fun invoke(verifyOtpRequestModel: VerifyOtpRequestModel): Flow<LiveDataResult<ProfileResponseModel>> =
		authenticationRepository.verifyOtp(verifyOtpRequestModel)
}

class GetRegisterUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
		(RegisterRequestModel) -> Flow<LiveDataResult<ProfileResponseModel>> {
	override fun invoke(registerRequestModel: RegisterRequestModel): Flow<LiveDataResult<ProfileResponseModel>> =
		authenticationRepository.register(registerRequestModel)
}

class GetIsUserLoggedInUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
		() -> Boolean {
	override fun invoke(): Boolean = authenticationRepository.isUserLoggedIn()
}