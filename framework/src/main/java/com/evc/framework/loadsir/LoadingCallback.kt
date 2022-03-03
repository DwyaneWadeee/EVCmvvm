package com.evc.framework.loadsir

import com.evc.framework.R
import com.kingja.loadsir.callback.Callback

/**
 * @Description: 类描述
 * @Author: EvanChan
 * @CreateDate: 2022/3/2 3:00 PM
 * @m-mail: dadaintheair@gmail.com
 */
class LoadingCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.stateview_loading
    }
}