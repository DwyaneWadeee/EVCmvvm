package com.evc.evcmvvm.ui.demopage

import android.os.Bundle
import com.evc.evcmvvm.R
import com.evc.evcmvvm.ui.main.MainFragment
import com.evc.framework.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_test.*

/**
 * @Description: 类描述
 * @Author: EvanChan
 * @CreateDate: 2022/2/23 10:28 AM
 * @m-mail: dadaintheair@gmail.com
 */
class DemoFragment : BaseFragment() {
    private val pageText by lazy { arguments?.getString(PAGETEXT) }

    companion object{
        private const val PAGETEXT = "pageText"

        fun newInstance(pageText: String): DemoFragment {
            val fragment = DemoFragment()
            val bundle = Bundle()
            bundle.putString(PAGETEXT, pageText)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_test
    }

    override fun initView() {

    }

    override fun initData() {
        tv_test.text = pageText
    }
}