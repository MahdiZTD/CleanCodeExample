package com.mahdi.ztd.infrastructure.remote.retrofithelper

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */
object NetworkConstants {
    const val BASE_URL_DEBUG = "http://apitest.televisit24.com/"
    const val BASE_URL_PRODUCTION = "http://apitest.televisit24.com"



    object Authentication {
        const val LOGIN = "/api/activator"
        const val VERIFY_OTP = "/api/Account"
        const val REGISTER = "/api/patient/register"
    }


}