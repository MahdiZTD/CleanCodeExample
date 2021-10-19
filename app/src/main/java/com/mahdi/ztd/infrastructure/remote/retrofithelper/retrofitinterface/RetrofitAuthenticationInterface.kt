package com.mahdi.ztd.infrastructure.remote.retrofithelper.retrofitinterface

import com.mahdi.ztd.infrastructure.domain.model.DummyResponse
import com.mahdi.ztd.infrastructure.domain.model.requests.RegisterRequestModel
import com.mahdi.ztd.infrastructure.domain.model.requests.VerifyOtpRequestModel
import com.mahdi.ztd.infrastructure.domain.model.responses.ProfileResponseModel
import com.mahdi.ztd.infrastructure.remote.retrofithelper.NetworkConstants
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */


interface RetrofitAuthenticationInterface {

	@POST(NetworkConstants.Authentication.LOGIN)
	suspend fun login(@Query("cellphone") cellphone: String): DummyResponse

	@POST(NetworkConstants.Authentication.VERIFY_OTP)
	suspend fun verifyOtp(@Body verifyOtpRequestModel: VerifyOtpRequestModel): ProfileResponseModel

	@POST(NetworkConstants.Authentication.REGISTER)
	suspend fun register(@Body registerRequestModel: RegisterRequestModel): ProfileResponseModel


}