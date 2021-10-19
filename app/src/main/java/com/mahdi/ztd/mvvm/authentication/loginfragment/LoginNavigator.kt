package com.mahdi.ztd.mvvm.authentication.loginfragment


/**
 * Created by Mahdi ZTD
 *  on 2/19/2021 .
 *  Email: mz@imagineOn.de
 */
interface LoginNavigator {
	fun navigateToVerifyOtp()
	fun getErrorOfEmptyFields(): String
}