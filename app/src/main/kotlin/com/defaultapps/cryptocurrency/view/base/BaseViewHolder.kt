package com.defaultapps.cryptocurrency.view.base

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife


abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    init {
        ButterKnife.bind(this, view)
    }
}
