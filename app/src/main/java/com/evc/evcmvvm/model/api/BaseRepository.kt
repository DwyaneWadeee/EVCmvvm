package com.evc.evcmvvm.model.api

import com.evc.evcmvvm.model.bean.base.WanResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException
import com.evc.evcmvvm.model.bean.base.Result
/**
 *
 * @Package: com.evc.evcmvvm.model.api
 * @Description: BaseRepository
 * @Author: EvanChan
 * @CreateDate: 2022/2/10 10:52 AM
 * @m-mail: dadaintheair@gmail.com
 */
open class BaseRepository {
    suspend fun <T : Any> apiCall(call: suspend () -> WanResponse<T>): WanResponse<T> {
        return call.invoke()
    }

    //抓住异常
    suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
            Result.Error(IOException(errorMessage, e))
        }
    }

    suspend fun <T : Any> executeResponse(response: WanResponse<T>,
                                          successBlock: (suspend CoroutineScope.() -> Unit)? = null,
                                          errorBlock: (suspend CoroutineScope.() -> Unit)? = null): Result<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let { it() }
                Result.Error(IOException(response.errorMsg))
            } else {
                successBlock?.let { it() }
                Result.Success(response.data)
            }
        }
    }
}