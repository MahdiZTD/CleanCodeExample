package com.mahdi.ztd.mvvm.authentication.authenticationactivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.library.baseAdapters.BR
import com.mahdi.ztd.R
import com.mahdi.ztd.databinding.ActivityAuthenticationBinding
import com.mahdi.ztd.mvvm.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */

@ActivityScoped
@AndroidEntryPoint
class AuthenticationActivity: BaseActivity<ActivityAuthenticationBinding, AuthenticationViewModel>(),AuthenticationNavigator {

    private val authenticationViewModel:AuthenticationViewModel by viewModels()

    override val bindingVariable: Int
        get() = BR.vm
    override val layoutId: Int
        get() = R.layout.activity_authentication
    override val mViewModel: AuthenticationViewModel
        get() = authenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setNavigator(this)
    }
}