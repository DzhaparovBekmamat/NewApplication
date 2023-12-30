package com.chihuahua.pokereducation.ui.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE

class Preferences(context: Context) {
    companion object {
        const val PREFERENCES_NAME = "MyPreferences"
    }

    private var preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)

    fun saveBoardState() {
        preferences.edit().putBoolean("isShow", true).apply()
    }

    fun isBoardShow(): Boolean {
        return preferences.getBoolean("isShow", false)
    }
}
