package com.evc.framework.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 * @Description: BaseVMActivity基类
 * @Author: EvanChan
 * @CreateDate: 2022/2/25 11:40 AM
 * @m-mail: dadaintheair@gmail.com
 */
abstract class BaseVMActivity : AppCompatActivity() {
    lateinit var loadService: LoadService<Any>
    protected inline fun <reified T : ViewDataBinding> binding(@LayoutRes resId: Int):
            Lazy<T> = lazy { DataBindingUtil.setContentView<T>(this, resId).apply {
        lifecycleOwner = this@BaseVMActivity
    }}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startObserve()
        initView()
        initData()
//        initListener()
        loadService = LoadSir.getDefault().register(this, Callback.OnReloadListener {
            refresh()
        })
    }

//    open fun initListener() {}
    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()

    abstract fun refresh()
}