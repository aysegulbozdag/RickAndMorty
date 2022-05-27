package com.example.rickandmorty

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun customBinding(iv:ImageView, url:String?) {
    Glide.with(iv.context)
        .load(url)
        .into(iv)
}