package com.aymen.cafeyn.global.extension

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["imagePath", "holder"], requireAll = true)
fun setImageUri(imageView: ImageView, imagePath: String?, placeHolder: Drawable) {
    if (imagePath.isNullOrEmpty())
        imageView.setImageDrawable(placeHolder)
    else
        imageView.setImageURI(Uri.parse(imagePath))
}