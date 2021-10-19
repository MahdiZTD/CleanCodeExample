package com.mahdi.ztd.mvvm.authentication.registerfragment


/**
 * Created by Mahdi ZTD
 *  on 2/21/2021 .
 *  Email: mz@imagineOn.de
 */
interface RegisterNavigator {

	fun getErrorOfEmptyFields(): String
	fun navigateToMainPage()
	fun getErrorPasswordNotMatched(): String
}