package com.mahdi.ztd.infrastructure.remote.apihelper.authentication

import com.mahdi.ztd.infrastructure.domain.model.DummyResponse
import com.mahdi.ztd.infrastructure.domain.model.requests.RegisterRequestModel
import com.mahdi.ztd.infrastructure.domain.model.requests.VerifyOtpRequestModel
import com.mahdi.ztd.infrastructure.domain.model.responses.ProfileResponseModel
import com.mahdi.ztd.infrastructure.remote.retrofithelper.retrofitinterface.RetrofitAuthenticationInterface
import javax.inject.Inject

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */

interface AuthenticationApiHelper {
	suspend fun login(cellphone: String): DummyResponse
	suspend fun verifyOtp(verifyOtpRequestModel: VerifyOtpRequestModel): ProfileResponseModel
	suspend fun register(registerRequestModel: RegisterRequestModel): ProfileResponseModel
}

class AuthenticationApiHelperImpl @Inject constructor(
	private val retrofitAuthenticationInterface: RetrofitAuthenticationInterface
) : AuthenticationApiHelper {
	override suspend fun login(cellphone: String): DummyResponse =
		retrofitAuthenticationInterface.login(cellphone)

	override suspend fun verifyOtp(verifyOtpRequestModel: VerifyOtpRequestModel): ProfileResponseModel =
		retrofitAuthenticationInterface.verifyOtp(verifyOtpRequestModel)

	override suspend fun register(registerRequestModel: RegisterRequestModel): ProfileResponseModel =
		retrofitAuthenticationInterface.register(registerRequestModel)

}