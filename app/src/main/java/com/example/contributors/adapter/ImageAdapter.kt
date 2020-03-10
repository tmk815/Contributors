package com.example.contributors.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ImageAdapter {
    @JvmStatic
    @BindingAdapter("loadImg")
    fun ImageView.loadImg(url: String?) {
        Picasso.get().load(url).into(this)
    }
}
