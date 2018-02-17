package com.defaultapps.cryptocurrency.utils

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("DEPRECATION")
@Singleton
class ResUtils @Inject constructor(private val context: Context) {

    fun getColor(@ColorRes colorId: Int) = context.resources.getColor(colorId)

    fun getDrawable(@DrawableRes drawableId: Int) = context.resources.getDrawable(drawableId)

    fun getString(@StringRes stringId: Int, arg: String? = null) = context.getString(stringId, arg)

    fun getString(@StringRes stringId: Int, arg: Int? = null) = context.getString(stringId, arg)
}
