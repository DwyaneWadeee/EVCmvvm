package com.evc.evcmvvm

import android.app.Application
import android.content.Context
import com.evc.evcmvvm.di.appModule
import com.evc.framework.loadsir.EmptyCallback
import com.evc.framework.loadsir.ErrorCallback
import com.evc.framework.loadsir.LoadingCallback
import com.kingja.loadsir.core.LoadSir
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.properties.Delegates

/**
 * @Description: App
 * @Author: EvanChan
 * @CreateDate: 2022/2/21 5:40 PM
 * @m-mail: dadaintheair@gmail.com
 */
class App : Application(){
    companion object{
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
        
        //Loadsir配置
        LoadSir.beginBuilder()
            .addCallback(EmptyCallback())
            .addCallback(ErrorCallback())
            .addCallback(LoadingCallback())
            .setDefaultCallback(LoadingCallback::class.java)//设置默认状态页
                .commit()
    }
}