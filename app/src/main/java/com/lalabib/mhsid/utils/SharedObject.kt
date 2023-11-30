package com.lalabib.mhsid.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lalabib.mhsid.R

object SharedObject {

    const val baseUrl = "https://www.riizeis.me"
    const val menuHome = 1
    const val menuDetail = 2

    fun loadAvatar(imageView: ImageView, avatar: String?) {
        Glide.with(imageView.context)
            .load(avatar)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_img)
            )
            .into(imageView)
    }
}