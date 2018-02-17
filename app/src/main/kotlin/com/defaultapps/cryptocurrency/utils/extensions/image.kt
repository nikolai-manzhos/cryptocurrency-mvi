package com.defaultapps.cryptocurrency.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadSimple(url: String) {
    Glide
            .with(this)
            .load(url)
            .into(this)
}
