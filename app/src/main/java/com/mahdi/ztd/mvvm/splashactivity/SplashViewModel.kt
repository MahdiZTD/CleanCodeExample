package com.mahdi.ztd.mvvm.splashactivity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.mahdi.ztd.infrastructure.domain.usecase.GetIsUserLoggedInUseCase
import com.mahdi.ztd.mvvm.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by Mahdi ZTD
 *  on 2/21/2021 .
 *  Email: mz@imagineOn.de
 */

class SplashViewModel @ViewModelInject constructor(
	private val getIsUserLoggedInUseCase: GetIsUserLoggedInUseCase
	):BaseViewModel<SplashNavigator>(){

	init {
		viewModelScope.launch {
			mLoadingState.value = true
			delay(2000)
			mLoadingState.value = false
			if (getIsUserLoggedInUseCase.invoke()) {
				getNavigator().navigateToHomePage()
			} else {
				getNavigator().navigateToLoginPage()
			}
		}
	}
}