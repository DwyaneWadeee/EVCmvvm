package com.evc.evcmvvm

import android.Manifest
import com.evc.framework.base.BaseActivity
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions
import toast

@RuntimePermissions
class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
    }

    override fun initData() {
        checkPermisson()
    }

    private fun checkPermisson() {
        showCameraWithPermissionCheck()
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    fun showCamera() {
        toast("已经获取Camera权限")
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun noCamera() {
        toast("nocamera")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

}