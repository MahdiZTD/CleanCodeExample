package com.mahdi.ztd.infrastructure.data.repositoryimpl

import com.mahdi.ztd.infrastructure.data.datasource.local.LocalAuthenticationDataSource
import com.mahdi.ztd.infrastructure.data.datasource.remote.RemoteAuthenticationDataSource
import com.mahdi.ztd.infrastructure.domain.domainutils.LiveDataResult
import com.mahdi.ztd.infrastructure.domain.domainutils.formatHttpErrorMessages
import com.mahdi.ztd.infrastructure.domain.model.requests.RegisterRequestModel
import com.mahdi.ztd.infrastructure.domain.model.requests.VerifyOtpRequestModel
import com.mahdi.ztd.infrastructure.domain.model.responses.ProfileResponseModel
import com.mahdi.ztd.infrastructure.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


/**
 * Created by Mahdi ZTD
 *  on 2/21/2021 .
 *  Email: mz@imagineOn.de
 */

class AuthenticationRepositoryImpl @Inject constructor(
	private val remoteAuthenticationDataSource: RemoteAuthenticationDataSource,
	private val localAuthenticationDataSource: LocalAuthenticationDataSource
) : AuthenticationRepository {
	override fun login(cellphone: String): Flow<LiveDataResult<Nothing>> =
		flow {
			emit(LiveDataResult.Loading)
			try {
				remoteAuthenticationDataSource.login(cellphone)
				emit(LiveDataResult.Success(null))
			} catch (exception: Exception) {
				emit(LiveDataResult.Failure(exception.formatHttpErrorMessages(), exception))
			}
		}

	override fun verifyOtp(verifyOtpRequestModel: VerifyOtpRequestModel): Flow<LiveDataResult<ProfileResponseModel>> =
		flow {
			emit(LiveDataResult.Loading)
			try {
				val response = remoteAuthenticationDataSource.verifyOtp(verifyOtpRequestModel)
				if (!response.password.isNullOrEmpty())
					localAuthenticationDataSource.saveAccessToken(response.password)
				emit(LiveDataResult.Success(response))
			} catch (exception: Exception) {
				emit(LiveDataResult.Failure(exception.formatHttpErrorMessages(), exception))
			}
		}

	override fun register(registerRequestModel: RegisterRequestModel): Flow<LiveDataResult<ProfileResponseModel>> =
		flow {
			emit(LiveDataResult.Loading)
			try {
				val response = remoteAuthenticationDataSource.register(registerRequestModel)
				if (!response.password.isNullOrEmpty())
					localAuthenticationDataSource.saveAccessToken(response.password)
				emit(LiveDataResult.Success(response))
			} catch (exception: Exception) {
				emit(LiveDataResult.Failure(exception.formatHttpErrorMessages(), exception))
			}
		}

	override fun isUserLoggedIn(): Boolean =
		!localAuthenticationDataSource.getAccessToken().isNullOrEmpty()

}