package com.mahdi.ztd.mvvm.authentication.loginfragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mahdi.ztd.infrastructure.domain.domainutils.LiveDataResult
import com.mahdi.ztd.infrastructure.domain.usecase.GetLoginUseCase
import com.mahdi.ztd.mvvm.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * Created by Mahdi ZTD
 *  on 2/19/2021 .
 *  Email: mz@imagineOn.de
 */

class LoginViewModel @ViewModelInject constructor(
	private val getLoginUseCase: GetLoginUseCase
) : BaseViewModel<LoginNavigator>() {


	val phoneNumber = MutableLiveData<String>("")

	fun onLoginClicked() {

		if(phoneNumber.value.isNullOrEmpty()){
			mGeneralErrorMessage.value = getNavigator().getErrorOfEmptyFields()
			return
		}

		viewModelScope.launch {
			getLoginUseCase.invoke(phoneNumber.value.orEmpty())
				.collect {result ->
					when (result) {
						is LiveDataResult.Success -> {
							mLoadingState.value = false
							getNavigator().navigateToVerifyOtp()
						}
						is LiveDataResult.Loading -> {
							mLoadingState.value = true
						}
						is LiveDataResult.Failure -> {
							mLoadingState.value = false
							mGeneralErrorMessage.value = result.message
						}
					}
				}
		}
	}
}