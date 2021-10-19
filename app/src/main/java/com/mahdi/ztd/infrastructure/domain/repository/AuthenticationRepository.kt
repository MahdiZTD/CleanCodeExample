package com.mahdi.ztd.infrastructure.domain.repository

import com.mahdi.ztd.infrastructure.domain.domainutils.LiveDataResult
import com.mahdi.ztd.infrastructure.domain.model.requests.RegisterRequestModel
import com.mahdi.ztd.infrastructure.domain.model.requests.VerifyOtpRequestModel
import com.mahdi.ztd.infrastructure.domain.model.responses.ProfileResponseModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */
interface AuthenticationRepository {
	fun login(cellphone: String): Flow<LiveDataResult<Nothing>>
	fun verifyOtp(verifyOtpRequestModel: VerifyOtpRequestModel): Flow<LiveDataResult<ProfileResponseModel>>
	fun register(registerRequestModel: RegisterRequestModel): Flow<LiveDataResult<ProfileResponseModel>>
	fun isUserLoggedIn(): Boolean
}