package com.mahdi.ztd.mvvm.authentication.verifiotpfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mahdi.ztd.R
import com.mahdi.ztd.databinding.FragmentVerifyOtpBinding
import com.mahdi.ztd.mvvm.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped


/**
 * Created by Mahdi ZTD
 *  on 2/21/2021 .
 *  Email: mz@imagineOn.de
 */

@FragmentScoped
@AndroidEntryPoint
class VerifyOtpFragment : BaseFragment<FragmentVerifyOtpBinding, VerifyOtpViewModel>(),
	VerifyOtpNavigator {

	private val verifyOtpViewModel: VerifyOtpViewModel by viewModels()

	private val args: VerifyOtpFragmentArgs by navArgs()

	override val bindingVariable: Int
		get() = BR.vm
	override val layoutId: Int
		get() = R.layout.fragment_verify_otp
	override val mViewModel: VerifyOtpViewModel
		get() = verifyOtpViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		mViewModel.setNavigator(this)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		mViewModel.phoneNumber = args.phoneNumber
	}

	override fun navigateToHomeScreen() {
		Toast.makeText(requireActivity(),"Done",Toast.LENGTH_SHORT).show()
	}

	override fun navigateToRegister() {
		findNavController().navigate(VerifyOtpFragmentDirections.actionVerifyOtpFragmentToRegisterFragment())
	}
}