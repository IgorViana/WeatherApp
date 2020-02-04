package com.example.android.bringyouumbrella.webService

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun downloadImage(context: Context, url: String, imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .into(imageView)
}