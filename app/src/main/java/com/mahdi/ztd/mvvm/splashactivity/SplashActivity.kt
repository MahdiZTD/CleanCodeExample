package com.mahdi.ztd.mvvm.splashactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.library.baseAdapters.BR
import com.mahdi.ztd.R
import com.mahdi.ztd.databinding.ActivitySplashBinding
import com.mahdi.ztd.mvvm.authentication.authenticationactivity.AuthenticationActivity
import com.mahdi.ztd.mvvm.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped


/**
 * Created by Mahdi ZTD
 *  on 2/21/2021 .
 *  Email: mz@imagineOn.de
 */

@ActivityScoped
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {
	private val splashViewModel: SplashViewModel by viewModels()

	override val bindingVariable: Int
		get() = BR.vm
	override val layoutId: Int
		get() = R.layout.activity_splash
	override val mViewModel: SplashViewModel
		get() = splashViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		mViewModel.setNavigator(this)
	}

	override fun navigateToLoginPage() {
		startActivity(Intent(this, AuthenticationActivity::class.java))
	}

	override fun navigateToHomePage() {
		Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
	}
}