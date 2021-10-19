package com.mahdi.ztd.infrastructure.remote.datasourceimpl.authentication

import com.mahdi.ztd.infrastructure.data.datasource.remote.RemoteAuthenticationDataSource
import com.mahdi.ztd.infrastructure.domain.model.DummyResponse
import com.mahdi.ztd.infrastructure.domain.model.requests.RegisterRequestModel
import com.mahdi.ztd.infrastructure.domain.model.requests.VerifyOtpRequestModel
import com.mahdi.ztd.infrastructure.domain.model.responses.ProfileResponseModel
import com.mahdi.ztd.infrastructure.remote.apihelper.authentication.AuthenticationApiHelper
import javax.inject.Inject

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */
class RemoteAuthenticationDataSourceImpl @Inject constructor(private val authenticationApiHelper: AuthenticationApiHelper) :
	RemoteAuthenticationDataSource {
	override suspend fun login(cellphone: String): DummyResponse =
		authenticationApiHelper.login(cellphone)

	override suspend fun verifyOtp(verifyOtpRequestModel: VerifyOtpRequestModel): ProfileResponseModel =
		authenticationApiHelper.verifyOtp(verifyOtpRequestModel)

	override suspend fun register(registerRequestModel: RegisterRequestModel): ProfileResponseModel =
		authenticationApiHelper.register(registerRequestModel)
}