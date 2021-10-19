package com.mahdi.ztd.mvvm.base

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mahdi.ztd.R
import com.mahdi.ztd.infrastructure.local.prefhelper.PREFERENCES_NAME
import com.mahdi.ztd.infrastructure.local.prefhelper.PREFERENCES_USER_REGION
import com.mahdi.ztd.mvvm.dialogs.GeneralMessageDialog
import com.mahdi.ztd.mvvm.viewutils.PermissionUtils
import com.mahdi.ztd.mvvm.viewutils.observe
import com.mahdi.ztd.mvvm.viewutils.setLocale

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(),
	BaseFragment.Callback {

	private var _binding: T? = null

	val viewDataBinding get() = _binding!!

	/**
	 * Override for set binding variable
	 *
	 * @return variable id
	 */
	abstract val bindingVariable: Int

	/**
	 * @return layout resource id
	 */
	@get:LayoutRes
	abstract val layoutId: Int

	/**
	 * Override for set view model
	 *
	 * @return view model instance
	 */
	abstract val mViewModel: V

	private var mLoadingProgressDialog: Dialog? = null

	override fun attachBaseContext(base: Context?) {
		if (base == null) super.attachBaseContext(base)
		else {
			val mPrefs: SharedPreferences = base.getSharedPreferences(
				PREFERENCES_NAME,
				Context.MODE_PRIVATE
			)
			mPrefs.getString(PREFERENCES_USER_REGION, "fa")?.let {
				super.attachBaseContext(setLocale(base, it))
				return
			}
			super.attachBaseContext(base)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
			&& Build.VERSION.SDK_INT < Build.VERSION_CODES.M
		) {
			window.statusBarColor = ContextCompat.getColor(this, R.color.black)
		}
		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
		performDataBinding()
		observe(mViewModel.mLoadingState, ::observeLoadingState)
		observe(mViewModel.mGeneralErrorMessage, ::observeErrorMessage)
	}

	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<String>,
		grantResults: IntArray
	) {
		PermissionUtils.onRequestPermissionResult(this, requestCode, permissions, grantResults)
	}

	override fun onActivityResult(
		requestCode: Int,
		resultCode: Int,
		data: Intent?
	) {
		super.onActivityResult(requestCode, resultCode, data)
		PermissionUtils.onActivityResult(this, requestCode)
	}

	override fun onFragmentAttached() {

	}


	override fun onFragmentDetached(tag: String) {

	}

	private fun performDataBinding() {
		_binding = DataBindingUtil.setContentView(this, layoutId)
		viewDataBinding.setVariable(bindingVariable, mViewModel)
		viewDataBinding.executePendingBindings()
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

	private fun observeErrorMessage(errorMessage: String?) {
		GeneralMessageDialog.Builder()
			.setCancelable(true)
			.setDuration(2000)
			.setTitle(getString(R.string.error))
			.setType(GeneralMessageDialog.DialogType.ERROR)
			.setDescription(errorMessage ?: getString(R.string.general_error_message))
			.build().show(supportFragmentManager, this.javaClass.simpleName)
	}

	private fun observeLoadingState(loadingState: Boolean?) {
		when (loadingState) {
			true -> {
				showLoading()
			}
			false -> {
				hideLoading()
			}
		}
	}

	private fun initialLoadingDialog() {
		if (mLoadingProgressDialog == null) {
			val inflater =
				getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
			val view = inflater.inflate(R.layout.dialog_loading, null)
			mLoadingProgressDialog = AlertDialog
				.Builder(this)
				.setView(view)
				.setCancelable(false)
				.create()
			mLoadingProgressDialog?.window?.setBackgroundDrawableResource(R.color.transparent)
		}
	}

	private fun showLoading() {
		if (mLoadingProgressDialog?.isShowing == false) {
			mLoadingProgressDialog?.show()
		}
	}

	private fun hideLoading() {
		if (mLoadingProgressDialog?.isShowing == true) {
			mLoadingProgressDialog?.dismiss()
		}
	}

	fun hideKeyboard() {
		val view = this.currentFocus
		if (view != null) {
			val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
			imm.hideSoftInputFromWindow(view.windowToken, 0)
		}
	}

}