package com.codexo.darktheme

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat

var themeDrawable = R.drawable.ic_android

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applyTheme()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.night_mode) {
            chooseThemeDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)

        menu?.findItem(R.id.night_mode)
            ?.setIcon(ContextCompat.getDrawable(this, themeDrawable))
        return super.onCreateOptionsMenu(menu)
    }

    private fun chooseThemeDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.select_theme))
        val themeModes: Array<String> = resources.getStringArray(R.array.theme_modes)
        val itemSelected = Theme(this).themeMode
        builder.setSingleChoiceItems(themeModes, itemSelected) { dialog, which ->
            when (which) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    Theme(this).themeMode = 0
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    Theme(this).themeMode = 1
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                2 -> {
                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                        AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                    } else {
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    }
                    Theme(this).themeMode = 2
                    delegate.applyDayNight()
                    dialog.dismiss()
                }

            }
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun applyTheme() {
        when (Theme(this).themeMode) {
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
                themeDrawable = R.drawable.ic_light
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
                themeDrawable = R.drawable.ic_night
            }
            2 -> {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                } else {
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                }
                themeDrawable = R.drawable.ic_android
                delegate.applyDayNight()
            }
        }
    }
}