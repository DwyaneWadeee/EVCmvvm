package com.evc.evcmvvm.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.evc.evcmvvm.model.api.ProjectRepository
import com.evc.evcmvvm.model.bean.ArticleList
import com.evc.framework.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.evc.evcmvvm.model.bean.base.Result

/**
 * @Description: 类描述
 * @Author: 首页VM
 * @CreateDate: 2022/2/21 4:26 PM
 * @m-mail: dadaintheair@gmail.com
 */
class ProjectViewModel(
    private val projectRepository: ProjectRepository
    ):BaseViewModel(){

    private val _uiState = MutableLiveData<ArticleUiModel>()
    val uiState: LiveData<ArticleUiModel> get() = _uiState

    private var currentPage = 0

//    val refresh: () -> Unit = {
//        getArticleList(true)
//    }
    val refresh: () -> Unit = { getArticleList(true)}

    /**
     * 获取文章列表
     */
    fun getArticleList( isRefresh: Boolean = false, cid: Int = 0){
        viewModelScope.launch(Dispatchers.Main){
            emitArticleUiState(true)

            if (isRefresh) currentPage =  0

            val result = projectRepository.getLastedProject(currentPage)

            if (result is Result.Success){
                val articleList = result.data
                if (articleList.offset>= articleList.total){
                    emitArticleUiState(showLoading = false,showEnd = true)
                    return@launch
                }
                currentPage++
                emitArticleUiState(showLoading = false,showSuccess = articleList, isRefresh = isRefresh)
            }else if (result is Result.Error){
                emitArticleUiState(showLoading = false,showError = result.exception.message)
            }
        }
    }

    /**
     * 改变UI状态
     */
    private fun emitArticleUiState(
        showLoading: Boolean = false,
        showError: String? = null,
        showSuccess: ArticleList? = null,
        showEnd: Boolean = false,
        isRefresh: Boolean = false,
        needLogin: Boolean? = null
    ) {
        val uiModel = ArticleUiModel(showLoading, showError, showSuccess, showEnd, isRefresh, needLogin)
        _uiState.value = uiModel
    }

    data class ArticleUiModel(
        val showLoading: Boolean,
        val showError: String?,
        val showSuccess: ArticleList?,
        val showEnd: Boolean, // 加载更多
        val isRefresh: Boolean, // 刷新
        val needLogin: Boolean? = null
    )
}