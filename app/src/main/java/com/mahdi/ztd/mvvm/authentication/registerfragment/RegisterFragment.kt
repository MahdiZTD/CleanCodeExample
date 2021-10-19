package com.mahdi.ztd.mvvm.authentication.registerfragment

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import com.mahdi.ztd.R
import com.mahdi.ztd.databinding.FragmentRegisterBinding
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
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(),
	RegisterNavigator {
	private val registerViewModel: RegisterViewModel by viewModels()
	override val bindingVariable: Int
		get() = BR.vm
	override val layoutId: Int
		get() = R.layout.fragment_register
	override val mViewModel: RegisterViewModel
		get() = registerViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		mViewModel.setNavigator(this)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		mViewModel.genders.value = resources.getStringArray(R.array.gender).toMutableList()

		viewDataBinding.spinnerGender.onItemSelectedListener =
			object : AdapterView.OnItemSelectedListener {
				override fun onItemSelected(
					parent: AdapterView<*>?,
					view: View?,
					position: Int,
					id: Long
				) {
					mViewModel.selectedGender = position
				}

				override fun onNothingSelected(parent: AdapterView<*>?) {

				}

			}
	}

	override fun getErrorOfEmptyFields(): String = getString(R.string.error_fields_are_empty)
	override fun getErrorPasswordNotMatched(): String = getString(R.string.error_password_not_matched)

	override fun navigateToMainPage() =
		Toast.makeText(requireActivity(),"Done",Toast.LENGTH_SHORT).show()

}