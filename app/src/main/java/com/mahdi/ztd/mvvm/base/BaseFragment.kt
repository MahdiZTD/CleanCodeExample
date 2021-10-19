package com.mahdi.ztd.mvvm.base

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.mahdi.ztd.R
import com.mahdi.ztd.mvvm.dialogs.GeneralMessageDialog
import com.mahdi.ztd.mvvm.viewutils.observe

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {

    var baseActivity: BaseActivity<*, *>? = null
        private set

    private var _binding: T? = null

    val viewDataBinding get() = _binding!!

    private var viewModel: V? = null

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val activity = context as BaseActivity<*, *>?
            this.baseActivity = activity as BaseActivity<*, *>
            activity.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = mViewModel
        setHasOptionsMenu(false)
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(false)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        val rootView = viewDataBinding.root
        return rootView
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(bindingVariable, mViewModel)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.executePendingBindings()
        initialLoadingDialog()
        observe(mViewModel.mLoadingState, ::observeLoadingState)
        observe(mViewModel.mGeneralErrorMessage, ::observeErrorMessage)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }

    private fun observeErrorMessage(errorMessage: String?) {
        GeneralMessageDialog.Builder()
            .setCancelable(true)
            .setDuration(2000)
            .setTitle(getString(R.string.error))
            .setType(GeneralMessageDialog.DialogType.ERROR)
            .setDescription(errorMessage ?: getString(R.string.general_error_message))
            .build().show(parentFragmentManager, this.javaClass.simpleName)
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
                requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.dialog_loading, null)
            mLoadingProgressDialog = AlertDialog
                .Builder(requireActivity())
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


}
