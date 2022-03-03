package com.evc.framework.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @Description: BaseActivity
 * @Author: EvanChan
 * @CreateDate: 2022/2/21 10:53 AM
 * @m-mail: dadaintheair@gmail.com
 */
abstract class BaseActivity : AppCompatActivity(){
    val currActivity by lazy { this }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView()
        initData()
    }

    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun initData()

}