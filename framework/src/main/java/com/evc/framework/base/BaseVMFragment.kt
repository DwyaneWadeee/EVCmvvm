package com.evc.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * @Description: BaseVMFragment
 * @Author: EvanChan
 * @CreateDate: 2022/2/21 2:54 PM
 * @m-mail: dadaintheair@gmail.com
 */
abstract class BaseVMFragment<T: ViewDataBinding>(@LayoutRes val layoutId:Int) : Fragment(layoutId){
    private  var _binding: T? = null
    protected val binding get() = _binding!!

    private fun <T:ViewDataBinding> binding( inflater:LayoutInflater,
                                             layoutId:Int, parent:ViewGroup?) : T{
        return DataBindingUtil.inflate<T>(inflater, layoutId, parent, false).apply {
            lifecycleOwner = this@BaseVMFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = binding(inflater,layoutId,container)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startObserve()
        initView()
        initData()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
}