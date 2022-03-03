package com.evc.evcmvvm.util

import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.request.RequestListener

/**
 * @Description: 类描述
 * @Author: EvanChan
 * @CreateDate: 2022/2/25 10:11 AM
 * @m-mail: dadaintheair@gmail.com
 */
fun <TranscodeType> RequestBuilder<TranscodeType>.placeHolderG(int:Int): RequestBuilder<TranscodeType> {
    return this.placeholder(int)
}


fun <TranscodeType> RequestBuilder<TranscodeType>.circleCropG(): RequestBuilder<TranscodeType> {
    return this.circleCrop()
}

fun <TranscodeType> RequestBuilder<TranscodeType>.transitionG(transitionOptions:
                                                             TransitionOptions<*, in TranscodeType>
): RequestBuilder<TranscodeType>
{
    return this.transition(transitionOptions)
}

fun <TranscodeType> RequestBuilder<TranscodeType>.overrideG(overrideWidth: Int,
                                                           overrideHeight:Int): RequestBuilder<TranscodeType>
{
    return this.override(overrideWidth,overrideHeight)
}

fun <TranscodeType> RequestBuilder<TranscodeType>.listenerG(requestListener:
                                                           RequestListener<TranscodeType>
): RequestBuilder<TranscodeType>
{
    return this.listener(requestListener)
}

fun <TranscodeType> RequestBuilder<TranscodeType>.intoG(imageview:
                                                       ImageView
)
{
    this.into(imageview)
}