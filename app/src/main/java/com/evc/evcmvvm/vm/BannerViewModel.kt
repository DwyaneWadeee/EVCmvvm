package com.evc.evcmvvm.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.evc.evcmvvm.model.api.BannerRepository
import com.evc.evcmvvm.model.bean.Banner
import com.evc.framework.base.BaseViewModel
import com.evc.evcmvvm.model.bean.base.Result

/**
 * @Description: 类描述
 * @Author: EvanChan
 * @CreateDate: 2022/2/24 11:46 AM
 * @m-mail: dadaintheair@gmail.com
 */
class BannerViewModel(
    private val bannerRepository: BannerRepository)
    :BaseViewModel() {
    val mBanners : LiveData<List<Banner>> = liveData{
        kotlin.runCatching {
            val data = bannerRepository.getBanners()
            if (data is Result.Success) emit(data.data)
        }
    }

}