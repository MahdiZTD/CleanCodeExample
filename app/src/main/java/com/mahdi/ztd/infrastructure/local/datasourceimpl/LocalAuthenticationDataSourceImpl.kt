package com.mahdi.ztd.infrastructure.local.datasourceimpl

import com.mahdi.ztd.infrastructure.data.datasource.local.LocalAuthenticationDataSource
import com.mahdi.ztd.infrastructure.local.prefhelper.PreferenceHelper
import javax.inject.Inject

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */

class LocalAuthenticationDataSourceImpl @Inject constructor(
	private val preferenceHelper: PreferenceHelper
) : LocalAuthenticationDataSource {

	override fun getAccessToken(): String? {
		return preferenceHelper.getAccessToken()
	}

	override fun saveAccessToken(access: String) {
		preferenceHelper.saveAccessToken("Bearer $access")
	}

	override fun logoutAndDelete() {
		preferenceHelper.logoutAndDeletePref()
	}
}