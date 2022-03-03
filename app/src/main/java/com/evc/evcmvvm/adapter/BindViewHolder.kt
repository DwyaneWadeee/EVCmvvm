package com.evc.evcmvvm.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @Description: 类描述
 * @Author: EvanChan
 * @CreateDate: 2022/2/22 3:17 PM
 * @m-mail: dadaintheair@gmail.com
 */
class BindViewHolder(view: View) : BaseViewHolder(view) {
    val binding: ViewDataBinding
        get() = itemView.getTag(com.chad.library.R.id.BaseQuickAdapter_databinding_support) as ViewDataBinding
}