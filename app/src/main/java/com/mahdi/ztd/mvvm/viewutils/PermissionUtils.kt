package com.mahdi.ztd.mvvm.viewutils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.text.TextUtils
import androidx.core.app.ActivityCompat
import com.mahdi.ztd.R

/**
 * Created by Mahdi_ZareTahghighDoost(ZTD)
 *  on 11/26/2020 .
 *  Email: MahdiZTD@gmail.com
 */

object PermissionUtils {

    private val permissionReqList: MutableList<PermissionReq> = ArrayList()

    private const val WRITE_EXTERNAL_STORAGE = 200
    private const val CAMERA_PERMISSION_REQ_CODE = 201

    fun checkWritePermission(
        activity: Activity,
        callback: ReqPermissionCallback
    ) {
        checkPermission(
            activity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE,
            "We Need This Action For saving screen shot on you device",
            "We Cant Share device screen shot without saving",
            callback
        )
    }

    fun checkCamera(
        activity: Activity,
        callback: ReqPermissionCallback
    ) {
        checkPermission(
            activity,
            Manifest.permission.CAMERA,
            CAMERA_PERMISSION_REQ_CODE,
            "We need camera permission to locate your position",
            "We can't get use this feature without camera permission",
            callback
        )
    }

    interface ReqPermissionCallback {
        fun onResult(success: Boolean)
    }

    private fun hasPermission(context: Context, permission: String): Boolean =
        ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    private fun checkPermission(
        activity: Activity,
        permission: String?,
        reqCode: Int,
        reqReason: CharSequence?,
        rejectedMsg: CharSequence?,
        callback: ReqPermissionCallback
    ) {
        if (hasPermission(activity, permission!!)) {
            activity.window.decorView.post { callback.onResult(true) }
        } else {
            val shouldShowReqReason = ActivityCompat
                .shouldShowRequestPermissionRationale(activity, permission)
            val req = PermissionReq(
                activity, permission, reqCode, reqReason!!, rejectedMsg!!, callback
            )
            if (shouldShowReqReason) {
                showReqReason(req)
            } else {
                reqPermission(req)
            }
        }
    }

    private fun showReqReason(req: PermissionReq) {
        AlertDialog.Builder(req.activity)
            .setCancelable(false)
            .setMessage(req.reqReason)
            .setPositiveButton(
                R.string.ok
            ) { _, _ -> reqPermission(req) }
            .show()
    }

    private fun reqPermission(req: PermissionReq) {
        permissionReqList.add(req)
        ActivityCompat.requestPermissions(
            req.activity,
            arrayOf(req.permission),
            req.reqCode
        )
    }

    private fun showRejectedMsg(req: PermissionReq) {
        AlertDialog.Builder(req.activity)
            .setCancelable(false)
            .setMessage(req.rejectedMsg)
            .setPositiveButton(
                R.string.ok
            ) { _, _ ->
                req.callback.onResult(false)
                permissionReqList.remove(req)
            }
            .setNegativeButton(
                R.string.change_setting
            ) { _, _ -> openAppDetailSetting(req) }
            .show()
    }

    private fun openAppDetailSetting(req: PermissionReq) {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri: Uri = Uri.fromParts("package", req.activity.packageName, null)
        intent.data = uri
        req.activity.startActivityForResult(intent, req.reqCode)
    }

    fun onRequestPermissionResult(
        activity: Activity?,
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        var targetReq: PermissionReq? = null
        for (req in permissionReqList) {
            if (req.activity == activity
                && req.reqCode == requestCode && req.permission == permissions[0]
            ) {
                targetReq = req
                break
            }
        }
        if (targetReq != null) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                targetReq.callback.onResult(true)
                permissionReqList.remove(targetReq)
            } else {
                if (TextUtils.isEmpty(targetReq.rejectedMsg)) {
                    targetReq.callback.onResult(false)
                    permissionReqList.remove(targetReq)
                } else {
                    showRejectedMsg(targetReq)
                }
            }
        }
    }

    fun onActivityResult(
        activity: Activity?,
        reqCode: Int
    ) {
        var targetReq: PermissionReq? = null
        for (req in permissionReqList) {
            if (req.activity == activity
                && req.reqCode == reqCode
            ) {
                targetReq = req
                break
            }
        }
        if (targetReq != null) {
            if (hasPermission(targetReq.activity, targetReq.permission)) {
                targetReq.callback.onResult(true)
            } else {
                targetReq.callback.onResult(false)
            }
            permissionReqList.remove(targetReq)
        }
    }

    data class PermissionReq(
        val activity: Activity,
        val permission: String,
        val reqCode: Int,
        val reqReason: CharSequence,
        val rejectedMsg: CharSequence,
        val callback: ReqPermissionCallback
    )
}
