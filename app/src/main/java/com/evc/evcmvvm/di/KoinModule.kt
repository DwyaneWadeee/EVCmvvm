package com.evc.evcmvvm.di

import com.evc.evcmvvm.model.api.BannerRepository
import com.evc.evcmvvm.model.api.ProjectRepository
import com.evc.evcmvvm.vm.BannerViewModel
import com.evc.evcmvvm.vm.ProjectViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @Description: 类描述
 * @Author: EvanChan
 * @CreateDate: 2022/2/21 6:03 PM
 * @m-mail: dadaintheair@gmail.com
 */
val repositoryModule = module {
    single { ProjectRepository() }
    single { BannerRepository() }
}

val viewModelModule = module {
    viewModel { ProjectViewModel(get()) }
    viewModel { BannerViewModel(get()) }
}

val appModule = listOf(viewModelModule,repositoryModule)