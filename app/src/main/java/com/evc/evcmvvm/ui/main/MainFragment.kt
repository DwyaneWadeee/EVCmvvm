package com.evc.evcmvvm.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.evc.evcmvvm.BR
import com.evc.evcmvvm.R
import com.evc.evcmvvm.adapter.BaseBindAdapter
import com.evc.evcmvvm.databinding.FragmentHomeBinding
import com.evc.evcmvvm.model.bean.Article
import com.evc.evcmvvm.ui.browser.BrowserActivity
import com.evc.evcmvvm.util.Preference
import com.evc.evcmvvm.view.CustomLoadMoreView
import com.evc.evcmvvm.vm.ProjectViewModel
import com.evc.framework.base.BaseVMFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import toast

/**
 * @Description: 主页首页
 * @Author: EvanChan
 * @CreateDate: 2022/2/21 2:50 PM
 * @m-mail: dadaintheair@gmail.com
 */
class MainFragment : BaseVMFragment<FragmentHomeBinding>(R.layout.fragment_home){
    private val articleViewModel by viewModel<ProjectViewModel>()

    private val isLogin by Preference(Preference.IS_LOGIN, false)
    private val cid by lazy { arguments?.getInt(CID) }
    private val isLasted by lazy { arguments?.getBoolean(LASTED) } // 区分是最新项目 还是项目分类

    private val projectAdapter by lazy { BaseBindAdapter<Article>(R.layout.item_project, BR.article) }

    companion object{
        private const val CID = "projectCid"
        private const val LASTED = "lasted"
        fun newInstance(cid: Int, isLasted: Boolean): MainFragment {
            val fragment = MainFragment()
            val bundle = Bundle()
            bundle.putInt(CID, cid)
            bundle.putBoolean(LASTED, isLasted)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initView() {
        binding.adapter = projectAdapter
        projectRecycleView.adapter = projectAdapter
        val layoutManager = LinearLayoutManager(activity)
        projectRecycleView.layoutManager =layoutManager
        initRecyclerView()
    }

    private fun initRecyclerView() {
        projectRefreshLayout.setOnRefreshListener { refresh() }
        projectAdapter.run {
            setOnItemClickListener(object: BaseQuickAdapter.OnItemClickListener {
                override fun onItemClick(
                    adapter: BaseQuickAdapter<*, *>?,
                    view: View?,
                    position: Int
                ) {
                    activity?.toast("点击了："+position)
                    Navigation.findNavController(projectRecycleView).navigate(R.id.action_tab_to_browser, bundleOf(
                        BrowserActivity.URL to projectAdapter.data[position].link)
                    )
                }
            })
            setLoadMoreView(CustomLoadMoreView())
            setOnLoadMoreListener({ loadMore() }, projectRecycleView)
            onItemChildClickListener = this@MainFragment.onItemChildClickListener
        }
    }

    override fun initData() {
        refresh()
    }

    fun refresh() {
        projectAdapter.setEnableLoadMore(false)
        loadData(true)
    }

    private fun loadMore() {
        loadData(false)
    }

    private fun loadData(isRefresh: Boolean) {
//        isLasted?.run {
//            if (this) {
//                articleViewModel.getLatestProjectList(isRefresh)
//            } else {
//                cid?.let {
//                    articleViewModel.getProjectTypeDetailList(isRefresh, it)
//                }
//            }
//        }
        articleViewModel.getArticleList(isRefresh)
    }

    private val onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
        when (view.id) {
            R.id.articleStar -> {
//                if (isLogin) {
//                    projectAdapter.run {
//                        data[position].run {
//                            collect = !collect
//                            articleViewModel.collectArticle(id, collect)
//                        }
//                        notifyDataSetChanged()
//                    }
//                } else {
//                    Navigation.findNavController(projectRecycleView).navigate(R.id.action_tab_to_login)
//                }
                activity?.toast("点击了收藏")
            }
        }
    }


    override fun startObserve() {
        articleViewModel.uiState.observe(viewLifecycleOwner, Observer {
            projectRefreshLayout.isRefreshing = it.showLoading

            it.showSuccess?.let { list ->
                projectAdapter.run {
                    if (it.isRefresh) {
                        replaceData(list.datas)
                    } else {
                        addData(list.datas)
                    }
                    setEnableLoadMore(true)
                    loadMoreComplete()
                }
            }
            if (it.showEnd) projectAdapter.loadMoreEnd()
        })
    }

}
