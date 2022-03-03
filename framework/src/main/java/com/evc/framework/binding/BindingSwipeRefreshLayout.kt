package com.evc.framework.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.onRefresh(action: () -> Unit) {
    setOnRefreshListener { action() }
}

@BindingAdapter("isRefresh")
fun SwipeRefreshLayout.isRefresh(isRefresh: Boolean) {
    isRefreshing = isRefresh
}