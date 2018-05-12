package com.npes87184.pokeresearchdictionary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.webkit.WebView
import com.npes87184.pokeresearchdictionary.Utils.Keys

class LicenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if (prefs.getString(Keys.KEY_PREF_THEME, Keys.KEY_PREF_THEME_DARK) == Keys.KEY_PREF_THEME_DARK) {
            setTheme(R.style.AppThemeDark)
        } else {
            setTheme(R.style.AppThemeLight)
        }
        setContentView(R.layout.activity_license)

        val wv = findViewById<WebView>(R.id.webView)
        wv.loadUrl("file:///android_asset/license.html")
    }
}
