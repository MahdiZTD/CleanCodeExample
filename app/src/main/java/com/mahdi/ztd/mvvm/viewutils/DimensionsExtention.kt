package com.mahdi.ztd.mvvm.viewutils

import android.content.res.Resources
import android.util.TypedValue

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 12/3/2020 .
 *  Email: MahdiZTD@gmail.com
 */

fun Int.dp2Px(resources: Resources): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), resources.displayMetrics).toInt()

fun Double.format(digits: Int) = "%.${digits}f".format(this)
