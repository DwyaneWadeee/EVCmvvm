package com.evc.evcmvvm.model.api

import com.evc.evcmvvm.model.bean.ArticleList
import com.evc.evcmvvm.model.bean.base.Result
/**
 * @Description: ProjectRepository
 * @Author: EvanChan
 * @CreateDate: 2022/2/21 5:18 PM
 * @m-mail: dadaintheair@gmail.com
 */
class ProjectRepository : BaseRepository() {
    suspend fun getLastedProject(page: Int):Result<ArticleList>{
        return safeApiCall(call = {requestLastedProject(page)},errorMessage = "发生未知错误")
    }

    private suspend fun requestLastedProject(page: Int): Result<ArticleList> =
        executeResponse(RetrofitClient.service.getLastedProject(page))
}