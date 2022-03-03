package com.evc.evcmvvm.ui.splash

import android.content.Intent
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.evc.evcmvvm.MainActivity
import com.evc.evcmvvm.R
import com.evc.framework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Description: 类描述
 * @Author: EvanChan
 * @CreateDate: 2022/2/24 3:13 PM
 * @m-mail: dadaintheair@gmail.com
 */
class SplashActivity : BaseActivity() {
    override fun getLayoutResId()= R.layout.activity_splash

    override fun initView() {}

    override fun initData() {
        jumpMain()
    }

    private fun jumpMain() {
        GlobalScope.launch {
            delay(3000)
            startActivity(Intent(currActivity,MainActivity::class.java))
            finish()
        }
    }
}