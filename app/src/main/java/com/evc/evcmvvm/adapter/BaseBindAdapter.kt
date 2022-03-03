package com.evc.evcmvvm.adapter
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import com.evc.evcmvvm.R

/**
 * Created by luyao
 * on 2019/10/25 13:16
 */
open class BaseBindAdapter<ITEM>(layoutResId: Int, br: Int) : BaseQuickAdapter<ITEM, BaseBindAdapter.BindViewHolder>(layoutResId) {

    private val _br: Int = br

    override fun convert(helper: BindViewHolder, item: ITEM) {
        helper.binding.run {
            setVariable(_br, item)
            executePendingBindings()
        }
    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup?): View {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(mLayoutInflater, layoutResId, parent, false)
            ?: return super.getItemView(layoutResId, parent)
        return binding.root.apply {
            setTag(com.chad.library.R.id.BaseQuickAdapter_databinding_support, binding)
        }
    }

    class BindViewHolder(view: View) : BaseViewHolder(view) {
        val binding: ViewDataBinding
            get() = itemView.getTag(com.chad.library.R.id.BaseQuickAdapter_databinding_support) as ViewDataBinding
    }
}