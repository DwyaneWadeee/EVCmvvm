package com.evc.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @Description: BaseFragment
 * @Author: EvanChan
 * @CreateDate: 2022/2/21 10:57 AM
 * @m-mail: dadaintheair@gmail.com
 */
abstract class BaseFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initData()
        initListener()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun getLayoutResId(): Int

    abstract fun initView()
    abstract fun initData()
    open fun initListener(){}


}