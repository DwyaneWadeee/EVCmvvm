package com.evc.evcmvvm.ui

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.evc.evcmvvm.R
import com.evc.evcmvvm.ui.demopage.BannerFragment
import com.evc.evcmvvm.ui.demopage.DemoFragment
import com.evc.evcmvvm.ui.demopage.MenuFragment
import com.evc.evcmvvm.ui.main.MainFragment
import com.evc.framework.base.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_home_tab.*

/**
 * @Description: 主页
 * @Author: EvanChan
 * @CreateDate: 2022/2/21 11:50 AM
 * @m-mail: dadaintheair@gmail.com
 */
class HomeTabFragment : BaseFragment(){
    override fun getLayoutResId() = R.layout.fragment_home_tab
    override fun initData() {}

    override fun initView() {
        initViewPager()
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelected)
    }

    private val onNavigationItemSelected = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.home -> {
                switchFragment(0)
            }
            R.id.blog -> {
                switchFragment(1)
            }
            R.id.search -> {
                switchFragment(2)
            }
            R.id.project -> {
                switchFragment(3)
            }
            R.id.profile -> {
                switchFragment(4)
            }
        }
        true
    }

    private fun switchFragment(position: Int): Boolean {
        mainViewpager.setCurrentItem(position, false)
        return true
    }

    private fun initViewPager() {
        mainViewpager.isUserInputEnabled = false
        mainViewpager.offscreenPageLimit = 2
        mainViewpager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int) = when (position) {
                0 -> MainFragment.newInstance(1,true)
                1 -> BannerFragment()
                2 -> DemoFragment.newInstance("22")
                3 -> DemoFragment.newInstance("33")
                4 -> MenuFragment()
                else -> MainFragment()
            }

            override fun getItemCount() = 5
        }
    }
}