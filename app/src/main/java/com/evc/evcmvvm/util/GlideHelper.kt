package com.evc.evcmvvm.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.util.Preconditions

/**
 * @Description: 类描述
 * @Author: EvanChan
 * @CreateDate: 2022/2/24 4:50 PM
 * @m-mail: dadaintheair@gmail.com
 */
class GlideHelper(context: Context) {
    var ctx = context
    var request = Glide.with(ctx)

    fun  load(url:String): RequestBuilder<Drawable> {
        return request.load(url)
    }


}
