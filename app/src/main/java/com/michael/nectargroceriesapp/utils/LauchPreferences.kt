package com.michael.nectargroceriesapp.utils

import android.content.Context
import androidx.core.content.edit

fun isFirstLaunch(context: Context): Boolean {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    return prefs.getBoolean("is_first_launch", true)
}

fun setFirstLaunchDone(context: Context) {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    prefs.edit { putBoolean("is_first_launch", false) }
}