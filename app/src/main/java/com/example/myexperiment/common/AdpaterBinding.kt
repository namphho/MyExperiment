package com.example.myexperiment.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created by nampham on 5/9/21.
 */

@BindingAdapter("loadImage")
fun loadImage(iv: ImageView, imagePath: String) {
    Glide.with(iv.context).load(imagePath).into(iv)
}