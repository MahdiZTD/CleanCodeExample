package com.mahdi.ztd.mvvm.authentication.verifiotpfragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mahdi.ztd.infrastructure.domain.domainutils.LiveDataResult
import com.mahdi.ztd.infrastructure.domain.model.requests.VerifyOtpRequestModel
import com.mahdi.ztd.infrastructure.domain.usecase.GetVerifyOtpUseCase
import com.mahdi.ztd.mvvm.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * Created by Mahdi ZTD
 *  on 2/21/2021 .
 *  Email: mz@imagineOn.de
 */
class VerifyOtpViewModel @ViewModelInject constructor(
	private val getVerifyOtpUseCase: GetVerifyOtpUseCase
) : BaseViewModel<VerifyOtpNavigator>() {

	val verifyOtp = MutableLiveData<String>("")
	var phoneNumber = ""


	fun onConfirmClicked() {

		if (verifyOtp.value.isNullOrEmpty()) {
			mGeneralErrorMessage.value = "Please fill verify code"
			return
		}

		viewModelScope.launch {
			getVerifyOtpUseCase.invoke(
				VerifyOtpRequestModel(phoneNumber, "", "", verifyOtp.value.orEmpty(), 1)
			)
				.collect { result ->
					when (result) {
						is LiveDataResult.Success -> {
							mLoadingState.value = false
							if (result.value?.firstName.isNullOrEmpty()) {
								getNavigator().navigateToRegister()
							} else {
								getNavigator().navigateToHomeScreen()
							}
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