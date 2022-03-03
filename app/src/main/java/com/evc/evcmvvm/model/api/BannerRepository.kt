package com.evc.evcmvvm.model.api

import com.evc.evcmvvm.model.bean.Banner
import com.evc.evcmvvm.model.bean.base.Result
/**
 * @Description: 类描述
 * @Author: EvanChan
 * @CreateDate: 2022/2/24 11:44 AM
 * @m-mail: dadaintheair@gmail.com
 */
class BannerRepository :BaseRepository() {
    suspend fun getBanners(): Result<List<Banner>> {
        return  safeApiCall(call={requestBanners()},"")
    }

    private suspend fun requestBanners(): Result<List<Banner>> =
        executeResponse(RetrofitClient.service.getBanner())

}