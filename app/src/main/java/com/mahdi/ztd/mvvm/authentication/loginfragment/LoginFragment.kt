package com.mahdi.ztd.mvvm.authentication.loginfragment

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mahdi.ztd.R
import com.mahdi.ztd.databinding.FragmentLoginBinding
import com.mahdi.ztd.mvvm.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped


/**
 * Created by Mahdi ZTD
 *  on 2/19/2021 .
 *  Email: mz@imagineOn.de
 */
@FragmentScoped
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(), LoginNavigator {
	private val loginViewModel: LoginViewModel by viewModels()
	override val bindingVariable: Int
		get() = BR.vm
	override val layoutId: Int
		get() = R.layout.fragment_login
	override val mViewModel: LoginViewModel
		get() = loginViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		mViewModel.setNavigator(this)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}


	override fun navigateToVerifyOtp() {
		findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToVerifyOtpFragment(mViewModel.phoneNumber.value.orEmpty()))
	}

	override fun getErrorOfEmptyFields(): String  = getString(R.string.error_fields_are_empty)
}