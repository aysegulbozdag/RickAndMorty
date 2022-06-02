package com.example.rickandmorty.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.rickandmorty.R

@BindingAdapter("loadImage")
fun customBinding(iv:ImageView, url:String?) {
    Glide.with(iv.context)
        .load(url)
        .into(iv)
}

@BindingAdapter("loadFavCharacter")
fun favCharacterBinding(iv:ImageView?, isFavCharacter : Boolean) {
   if (isFavCharacter)
       iv?.setImageResource(R.drawable.ic_inline_save)
    else iv?.setImageResource(R.drawable.ic_save)
}