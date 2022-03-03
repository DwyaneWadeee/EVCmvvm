package com.evc.framework.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @Description: BaseViewModel
 * @Author: EvanChan
 * @CreateDate: 2022/2/21 4:20 PM
 * @m-mail: dadaintheair@gmail.com
 */
open class BaseViewModel :ViewModel() {
    //界面状态
    open class UiState<T>(
        val isLoading: Boolean = false,
        val isRefresh: Boolean = false,
        val isSuccess: T? = null,
        val isError: String?= null
    )

    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    suspend fun <T> launchOnIO(block: suspend CoroutineScope.() -> T) {
        withContext(Dispatchers.IO) {
            block
        }
    }
}