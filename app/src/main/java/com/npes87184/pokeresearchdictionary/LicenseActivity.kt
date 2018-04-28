package com.npes87184.pokeresearchdictionary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class LicenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_license)

        val wv = findViewById<WebView>(R.id.webView)
        wv.loadUrl("file:///android_asset/license.html")
    }
}
