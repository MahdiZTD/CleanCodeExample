package com.mahdi.ztd.infrastructure.domain.domainutils

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 7/12/20.
 *  Email: MahdiZTD@gmail.com
 */
sealed class LiveDataResult<out T> {
    object Loading : LiveDataResult<Nothing>()
    data class Success<out R>(val value: R?) : LiveDataResult<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable?
    ) : LiveDataResult<Nothing>()
}
