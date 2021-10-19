package com.mahdi.ztd.infrastructure.domain.domainutils

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.mahdi.ztd.infrastructure.domain.model.GeneralApiResponse
import retrofit2.HttpException

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/30/2020 .
 *  Email: MahdiZTD@gmail.com
 */

fun Exception.formatHttpErrorMessages(): String? {
	this.printStackTrace()
	return if (this is HttpException) {
		try {
			Gson().fromJson(
				this.response()?.errorBody()?.string(),
				GeneralApiResponse::class.java
			)?.message
		} catch (exception: JsonSyntaxException) {
			this.response()?.errorBody()?.string()
		}

	} else {
		null
	}
}