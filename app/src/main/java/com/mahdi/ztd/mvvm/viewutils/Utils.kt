package com.mahdi.ztd.mvvm.viewutils

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*


/**
 * Created by Mahdi ZTD
 *  on 3/2/2021 .
 *  Email: mz@imagineOn.de
 */

fun setLocale(context: Context?, language: String): Context? {
	var mContext = context
	val locale = Locale(language)
	Locale.setDefault(locale)
	val res = mContext?.resources
	val config = Configuration(res?.configuration)
	if (Build.VERSION.SDK_INT >= 17) {
		config.setLocale(locale)
		config.setLayoutDirection(locale)
		mContext = mContext?.createConfigurationContext(config)
	} else {
		config.locale = locale
		config.setLayoutDirection(locale)
		res?.updateConfiguration(config, res.displayMetrics)
	}
	return mContext
}