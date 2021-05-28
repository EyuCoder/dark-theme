package com.codexo.darktheme

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

class Theme(context: Context?) {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var themeMode = preferences.getInt(KEY_THEME, 2)
        set(value) = preferences.edit().putInt(KEY_THEME, value).apply()

    companion object {
        private const val KEY_THEME = "com.codexo.darktheme.select_theme"
    }
}