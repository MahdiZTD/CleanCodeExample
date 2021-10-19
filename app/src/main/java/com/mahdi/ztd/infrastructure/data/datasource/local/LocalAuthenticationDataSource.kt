package com.mahdi.ztd.infrastructure.data.datasource.local

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */
interface LocalAuthenticationDataSource {
	fun getAccessToken(): String?
	fun saveAccessToken(access: String)

	fun logoutAndDelete()
}