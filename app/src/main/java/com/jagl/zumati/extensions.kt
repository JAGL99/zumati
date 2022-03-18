package com.jagl.zumati

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String) = Glide.with(this).load(url).placeholder(R.drawable.no_user_image).into(this)
