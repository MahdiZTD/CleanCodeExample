package com.mahdi.ztd.mvvm.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */

abstract class BaseViewModel<N>() :
    ViewModel() {

    lateinit var mNavigator: WeakReference<N>

    val mLoadingState = MutableLiveData<Boolean>()
    val mGeneralErrorMessage = MutableLiveData<String>()

    fun getNavigator(): N {
        return mNavigator.get()!!
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }

    override fun onCleared() {
        super.onCleared()
    }

}