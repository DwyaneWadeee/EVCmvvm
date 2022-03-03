package com.evc.evcmvvm.model.api

import com.evc.evcmvvm.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Description: 类描述
 * @Author: EvanChan
 * @CreateDate: 2022/2/21 5:23 PM
 * @m-mail: dadaintheair@gmail.com
 */
abstract class BaseRetrofitClient {
    private val TIME_OUT = 5

    private val client :OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.BASIC
            }

            builder.addInterceptor(logging)
                .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            handleBuilder(builder)
            return builder.build()
        }

    protected abstract fun handleBuilder(builder: OkHttpClient.Builder)

    fun <Service> getService(serviceClass: Class<Service>, baseUrl: String): Service {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .baseUrl(baseUrl)
            .build().create(serviceClass)
    }
}