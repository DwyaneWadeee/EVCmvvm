package com.evc.evcmvvm.ui.demopage

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.evc.evcmvvm.BR
import com.evc.evcmvvm.R
import com.evc.evcmvvm.adapter.BaseBindAdapter
import com.evc.evcmvvm.databinding.ActivityDemoBinding
import com.evc.evcmvvm.databinding.FragmentHomeBinding
import com.evc.evcmvvm.model.bean.Article
import com.evc.evcmvvm.view.CustomLoadMoreView
import com.evc.evcmvvm.vm.ProjectViewModel
import com.evc.framework.base.BaseActivity
import com.evc.framework.base.BaseVMActivity
import com.evc.framework.loadsir.EmptyCallback
import com.evc.framework.loadsir.ErrorCallback
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import kotlinx.android.synthetic.main.activity_demo.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import toast

/**
 * @Description: 类描述
 * @Author: EvanChan
 * @CreateDate: 2022/2/25 11:36 AM
 * @m-mail: dadaintheair@gmail.com
 */
class DemoActivity : BaseVMActivity() {
    private val binding by binding<ActivityDemoBinding>(R.layout.activity_demo)
    private val articleViewModel by viewModel<ProjectViewModel>()
    private val projectAdapter by lazy {
        BaseBindAdapter<Article>(
            R.layout.item_project,
            BR.article
        )
    }

//    override fun getLayoutResId()=R.layout.activity_demo

    //    Lazy<T> = lazy { DataBindingUtil.setContentView<T>(this, resId).apply {
//        lifecycleOwner = this@BaseVMActivity
    override fun initView() {
//    val contentView =
//        DataBindingUtil.setContentView<ActivityDemoBinding>(this, R.layout.activity_demo)
        binding.run {
            adapter = projectAdapter
            viewmodels = articleViewModel
        }
        initRecyclerView()
//    contentView.adapter = projectAdapter
//        projectRefreshLayout.setOnRefreshListener {
//            articleViewModel.refresh
//        }
//        DataBindingUtil.setContentView<ActivityDemoBinding>(this, R.layout.activity_demo)
    }

    private fun initRecyclerView() {
        projectRecycleView.run {
            layoutManager = LinearLayoutManager(this@DemoActivity)
        }
        projectAdapter.run {
            setLoadMoreView(CustomLoadMoreView())
            setOnLoadMoreListener({ loadMore() }, projectRecycleView)
        }
    }

    private fun loadMore() {
        articleViewModel.getArticleList(false)
    }

    override fun initData() {
        refresh()
    }

     override fun refresh() {
        articleViewModel.getArticleList(true)
    }

    override fun startObserve() {
        articleViewModel.apply {
            uiState.observe(this@DemoActivity, Observer {
                it.showSuccess?.let { list ->
//                    loadService.showCallback(EmptyCallback::class.java)
                    loadService.showSuccess()
                    projectAdapter.setEnableLoadMore(false)
                    list.datas.forEach { it.collect = true }
                    projectAdapter.run {
                        if (it.isRefresh) replaceData(list.datas)
                        else addData(list.datas)
                        setEnableLoadMore(true)
                        loadMoreComplete()
                    }
                }

                if (it.showEnd) projectAdapter.loadMoreEnd()

                it.showError?.let { message ->
                    loadService.showCallback(ErrorCallback::class.java)
                    toast(if (message.isBlank()) "网络异常" else message)
                }
            })
        }
    }
}