package com.mahdi.ztd.mvvm

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.multidex.MultiDexApplication
import com.mahdi.ztd.infrastructure.local.prefhelper.PREFERENCES_NAME
import com.mahdi.ztd.infrastructure.local.prefhelper.PREFERENCES_USER_REGION
import com.mahdi.ztd.mvvm.viewutils.setLocale
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Mahdi ZTD
 *  on 2/21/2021 .
 *  Email: mz@imagineOn.de
 */
@HiltAndroidApp
class ExampleApp : MultiDexApplication() {

	private lateinit var mPrefs: SharedPreferences


	override fun onCreate() {
		super.onCreate()
		mPrefs = applicationContext.getSharedPreferences(
			PREFERENCES_NAME,
			Context.MODE_PRIVATE
		)
	}

	override fun attachBaseContext(base: Context?) {
		if (base == null) super.attachBaseContext(base)
		else {
			mPrefs = base.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
			mPrefs.getString(PREFERENCES_USER_REGION, "fa")?.let {
				super.attachBaseContext(setLocale(base, it))
				return
			}
			super.attachBaseContext(base)
		}
	}

	override fun onConfigurationChanged(newConfig: Configuration) {
		super.onConfigurationChanged(newConfig)
		mPrefs.getString(PREFERENCES_USER_REGION, "fa")?.let {
			setLocale(this, it)
		}
	}
}