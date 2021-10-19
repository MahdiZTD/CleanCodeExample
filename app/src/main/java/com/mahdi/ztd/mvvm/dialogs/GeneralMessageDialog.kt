package com.mahdi.ztd.mvvm.dialogs

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.mahdi.ztd.R
import com.mahdi.ztd.databinding.DialogMessageGeneralBinding
import com.mahdi.ztd.mvvm.viewutils.dp2Px
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 9/9/2020 .
 *  Email: MahdiZTD@gmail.com
 */

class GeneralMessageDialog : DialogFragment() {

    object DialogType {
        const val SUCCESS = 1
        const val ERROR = 2
        const val INFO = 3
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_DESCRIPTION = "extra_desc"
        const val EXTRA_CANCELABLE = "extra_cancelable"
        const val EXTRA_Y_POSITION = "extra_y_position"
        const val EXTRA_DURATION = "extra_duration"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.MessageDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        dialog?.window?.setGravity(Gravity.BOTTOM)
        val viewDataBinding = DataBindingUtil.inflate<DialogMessageGeneralBinding>(
            LayoutInflater.from(context), R.layout.dialog_message_general, container, false
        )

        val args = arguments ?: throw IllegalArgumentException("No Arguments!")

        val cancelable = args.getBoolean(EXTRA_CANCELABLE, true)
        val duration = args.getLong(EXTRA_DURATION)
        val type = args.getInt(EXTRA_TYPE)
        val title = args.getString(EXTRA_TITLE, null)
        val description = args.getString(EXTRA_DESCRIPTION, null)

        if (duration > 0L) {
            GlobalScope.launch {
                delay(duration)
                dialog?.dismiss()
            }
        }



        viewDataBinding.dialogTextViewTitle.text = title
        viewDataBinding.dialogTextViewDescription.text = description

        when (type) {
            DialogType.ERROR -> {
                viewDataBinding.viewType.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.shape_rect_red, null)
                viewDataBinding.imageStatus.setImageResource(R.drawable.svg_ic_error_triangle)
            }
            DialogType.SUCCESS -> {
                viewDataBinding.viewType.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.shape_rect_green, null)
                viewDataBinding.imageStatus.setImageResource(R.drawable.svg_ic_success_rectangle)
            }
            DialogType.INFO -> {
                viewDataBinding.viewType.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.shape_rect_blue, null)
                viewDataBinding.imageStatus.setImageResource(R.drawable.svg_ic_info_circle)
            }
        }

        val layoutParams =
            viewDataBinding.dialogCardContainer.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(
            20.dp2Px(resources),
            0,
            20.dp2Px(resources),
            args.getInt(EXTRA_Y_POSITION).dp2Px(resources)
        )
        viewDataBinding.dialogCardContainer.requestLayout()

        dialog?.setCanceledOnTouchOutside(cancelable)

        return viewDataBinding.root
    }


    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window!!.setLayout(width, height)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    class Builder {
        private var title: String? = null
        private var description: String? = null
        private var cancelable = true
        private var type: Int = 0
        private var yPosition: Int = 40
        private var duration: Long = 0

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setDescription(description: String): Builder {
            this.description = description
            return this
        }

        fun setCancelable(cancelable: Boolean): Builder {
            this.cancelable = cancelable
            return this
        }

        fun setType(type: Int): Builder {
            this.type = type
            return this
        }

        fun setY(y: Int): Builder {
            this.yPosition = y
            return this
        }

        fun setDuration(duration: Long): Builder {
            this.duration = duration
            return this
        }

        fun build(): GeneralMessageDialog {
            val dialog = GeneralMessageDialog()
            val bundle = Bundle()
            bundle.putString(EXTRA_TITLE, title)
            bundle.putString(EXTRA_DESCRIPTION, description)
            bundle.putBoolean(EXTRA_CANCELABLE, cancelable)
            bundle.putInt(EXTRA_Y_POSITION, yPosition)
            bundle.putInt(EXTRA_TYPE, type)
            bundle.putLong(EXTRA_DURATION, duration)
            dialog.arguments = bundle
            return dialog
        }
    }
}