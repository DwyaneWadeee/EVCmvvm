package com.evc.evcmvvm.ui.demopage

import android.view.ViewGroup
import android.widget.LinearLayout
import com.evc.evcmvvm.R
import com.evc.evcmvvm.databinding.FragmentBannerTestBinding
import com.evc.evcmvvm.model.bean.Banner
import com.evc.evcmvvm.util.GlideImageLoader
import com.evc.evcmvvm.vm.BannerViewModel
import com.evc.framework.base.BaseVMFragment
import com.evc.ktx.ext.dp2px
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_banner_test.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import toast

/**
 * @Description: 类描述
 * @Author: EvanChan
 * @CreateDate: 2022/2/24 11:16 AM
 * @m-mail: dadaintheair@gmail.com
 */
class BannerFragment :BaseVMFragment<FragmentBannerTestBinding>(R.layout.fragment_banner_test) {
    private val viewModel by viewModel<BannerViewModel>()

    private val bannerImages = mutableListOf<String>()
    private val bannerTitles = mutableListOf<String>()
    private val bannerUrls = mutableListOf<String>()
    private val banner by lazy{
        com.youth.banner.Banner(activity)
    }

    override fun initView() {
        rl_root.addView(banner)
        initBanner()
    }

    private fun initBanner() {
        banner.run {
            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, banner.dp2px(200))
            setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            setImageLoader(GlideImageLoader())
            setOnBannerListener { position->
                run{
                    toast(context,"跳转")
                }
            }
        }
    }

    override fun initData() {
        viewModel.mBanners
    }

    override fun startObserve() {
        //observe data
        viewModel.apply {
            mBanners.observe(viewLifecycleOwner,{
                it->it?.let{
                    setBanner(it)
            }
            })
        }
    }

    private fun setBanner(bannerList: List<Banner>) {
        for (banner in bannerList) {
            bannerImages.add(banner.imagePath)
            bannerTitles.add(banner.title)
            bannerUrls.add(banner.url)
        }
        banner.setImages(bannerImages)
            .setBannerTitles(bannerTitles)
            .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            .setDelayTime(3000)
        banner.start()
    }
    override fun onStart() {
        super.onStart()
        banner.startAutoPlay()
    }
    override fun onStop() {
        super.onStop()
        banner.stopAutoPlay()
    }

}