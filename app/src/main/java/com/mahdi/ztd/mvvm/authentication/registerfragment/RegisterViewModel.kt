package com.mahdi.ztd.mvvm.authentication.registerfragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mahdi.ztd.infrastructure.domain.domainutils.LiveDataResult
import com.mahdi.ztd.infrastructure.domain.model.requests.RegisterRequestModel
import com.mahdi.ztd.infrastructure.domain.usecase.GetRegisterUseCase
import com.mahdi.ztd.mvvm.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * Created by Mahdi ZTD
 *  on 2/21/2021 .
 *  Email: mz@imagineOn.de
 */
class RegisterViewModel @ViewModelInject constructor(
	private val getRegisterUseCase: GetRegisterUseCase
) : BaseViewModel<RegisterNavigator>() {


	var genders = MutableLiveData<MutableList<String>>()

	var selectedGender: Int? = 0

	val phoneNumber = MutableLiveData<String>()
	val name = MutableLiveData<String>()
	val lastName = MutableLiveData<String>()
	val password = MutableLiveData<String>()
	val rePassword = MutableLiveData<String>()

	fun registerClicked() {
		if (phoneNumber.value.isNullOrEmpty() ||
			name.value.isNullOrEmpty() ||
			lastName.value.isNullOrEmpty() ||
			password.value.isNullOrEmpty() ||
			rePassword.value.isNullOrEmpty()
		) {
			mGeneralErrorMessage.value = getNavigator().getErrorOfEmptyFields()
			return
		}

		if(password.value != rePassword.value){
			mGeneralErrorMessage.value = getNavigator().getErrorPasswordNotMatched()
			return
		}

		viewModelScope.launch {
			getRegisterUseCase.invoke(
				RegisterRequestModel(
					phoneNumber.value.orEmpty(),
					phoneNumber.value.orEmpty(),
					1,
					name.value.orEmpty(),
					selectedGender?:0,
					"0",
					lastName.value.orEmpty(),
					"",
					password.value.orEmpty()
				)
			).collect { result ->
				when (result) {
					is LiveDataResult.Success -> {
						mLoadingState.value = false
						getNavigator().navigateToMainPage()
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