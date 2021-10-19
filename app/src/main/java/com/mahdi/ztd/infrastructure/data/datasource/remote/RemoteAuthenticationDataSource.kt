package com.mahdi.ztd.infrastructure.data.datasource.remote

import com.mahdi.ztd.infrastructure.domain.model.DummyResponse
import com.mahdi.ztd.infrastructure.domain.model.requests.RegisterRequestModel
import com.mahdi.ztd.infrastructure.domain.model.requests.VerifyOtpRequestModel
import com.mahdi.ztd.infrastructure.domain.model.responses.ProfileResponseModel

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */
interface RemoteAuthenticationDataSource {
    suspend fun login( cellphone: String): DummyResponse
    suspend fun verifyOtp( verifyOtpRequestModel: VerifyOtpRequestModel): ProfileResponseModel
    suspend fun register( registerRequestModel: RegisterRequestModel): ProfileResponseModel
}