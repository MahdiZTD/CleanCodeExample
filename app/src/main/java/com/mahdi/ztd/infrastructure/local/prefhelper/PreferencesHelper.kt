package com.mahdi.ztd.infrastructure.local.prefhelper

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */

const val PREFERENCES_NAME = "TELEVISIT_PREF"

const val PREFERENCES_ACCESS_TOKEN_KEY = "access_token_key"
const val PREFERENCES_REFRESH_TOKEN_KEY = "refresh_token_key"
const val PREFERENCES_USER_REGION = "user_region"

interface PreferenceHelper {

    //region authentication
    fun saveAccessToken(access: String)
    fun saveRefreshToken(refresh: String)
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    //endregion

    fun saveUserRegion(region: String)
    fun getUserRegion(): String?

    fun logoutAndDeletePref()


}

class PreferenceHelperImpl @Inject constructor(
    context: Context
) : PreferenceHelper {

    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun saveAccessToken(access: String) =
        mPrefs.edit().putString(PREFERENCES_ACCESS_TOKEN_KEY, access).apply()

    override fun saveRefreshToken(refresh: String) =
        mPrefs.edit().putString(PREFERENCES_REFRESH_TOKEN_KEY, refresh).apply()

    override fun getAccessToken(): String? = mPrefs.getString(PREFERENCES_ACCESS_TOKEN_KEY, null)

    override fun getRefreshToken(): String? = mPrefs.getString(PREFERENCES_REFRESH_TOKEN_KEY, null)

    override fun logoutAndDeletePref() =
        mPrefs.edit().clear().apply()


    override fun saveUserRegion(region: String) {
        mPrefs.edit().putString(PREFERENCES_USER_REGION, region).apply()
    }

    override fun getUserRegion(): String? =
        mPrefs.getString(PREFERENCES_USER_REGION, "fa")

}