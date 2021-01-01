package com.effort.dindinnapplication.common

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun bindImageFromUrl(imageView: ImageView, url: String) {
    Picasso.get()
        .load(url)
        .into(imageView)
}

@BindingAdapter("hideIfZero")
fun hideIfZero(view: View, notEmpty: Boolean) {
    view.visibility = if (notEmpty) View.VISIBLE else View.GONE
}