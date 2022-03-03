package com.evc.evcmvvm.ui.demopage

import android.app.Activity
import android.content.Intent
import androidx.navigation.Navigation
import com.evc.evcmvvm.R
import com.evc.framework.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_menu.*
import toast

/**
 * @Description: 类描述
 * @Author: EvanChan
 * @CreateDate: 2022/2/25 11:07 AM
 * @m-mail: dadaintheair@gmail.com
 */
class MenuFragment :BaseFragment() {
    override fun getLayoutResId()= R.layout.fragment_menu

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initListener() {
        btn.setOnClickListener {
//            Navigation.findNavController(btn).navigate(R.id.action_tab_to_demo)
//            Navigation.findNavController(btn).navigate(R.id.action_tab_to_browser)

            activity?.toast("clcik")
            val intent = Intent(context, DemoActivity::class.java)
            startActivity(intent)
        }
    }
}