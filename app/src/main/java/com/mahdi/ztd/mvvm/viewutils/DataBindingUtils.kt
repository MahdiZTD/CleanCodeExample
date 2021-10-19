package com.mahdi.ztd.mvvm.viewutils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Button
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener


/**
 * Created by Mahdi ZTD
 *  on 2/21/2021 .
 *  Email: mz@imagineOn.de
 */
object DataBindingUtils {

	//	region spinner
	@BindingAdapter(value = ["selectedValue", "selectedValueAttrChanged"], requireAll = false)
	fun bindSpinnerData(
		appCompatSpinner: AppCompatSpinner?,
		newSelectedValue: Int?,
		newTextAttrChanged: InverseBindingListener
	) {
		appCompatSpinner?.onItemSelectedListener = object : OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View?,
				position: Int,
				id: Long
			) {
				newTextAttrChanged.onChange()
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {}
		}
		if (newSelectedValue != null) {
			appCompatSpinner?.setSelection(newSelectedValue, true)
		}
	}

	//	endregion

	@BindingAdapter("svgDrawableLeft", "svgDrawableRight", requireAll = false)
	@JvmStatic
	fun Button.attachDrawable(left: Drawable?, right: Drawable?) {
		setCompoundDrawablesRelativeWithIntrinsicBounds(left,null,right,null)
	}

}