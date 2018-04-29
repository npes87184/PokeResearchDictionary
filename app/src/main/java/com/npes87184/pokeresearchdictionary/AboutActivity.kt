package com.npes87184.pokeresearchdictionary

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element


class AboutActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val versionElement = Element()
        var versionNum = ""
        try {
            versionNum = this.packageManager.getPackageInfo(this.packageName, 0).versionName
        } catch (e: Exception) {
        }


        versionElement.title = getString(R.string.version_tag) + versionNum
        val authorElement = Element()
        authorElement.title = getString(R.string.author_tag) + getString(R.string.author_name)

        val emailElement = Element()
        emailElement.title = getString(R.string.contact)
        emailElement.iconDrawable = R.drawable.about_icon_email
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "npes87184@gmail.com", null))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.subject))
        emailIntent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.mail_body))
        emailElement.intent = emailIntent

        val webElement = Element()
        webElement.title = getString(R.string.web_tag)
        webElement.iconDrawable = R.drawable.about_icon_link
        val url = "https://npes87184.github.io"
        val webIntent = Intent(Intent.ACTION_VIEW)
        webIntent.data = Uri.parse(url)
        webElement.intent = webIntent

        /*
        val playStoreElement = Element()
        playStoreElement.setTitle(getString(R.string.rate))
        playStoreElement.setIconDrawable(R.drawable.about_icon_google_play)
        val uri = Uri.parse("market://details?id=com.npes87184.s2tdroid.donate")
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        playStoreElement.intent = goToMarket
        */

        val licenseElement = Element()
        licenseElement.title = "Open Source License"
        licenseElement.iconDrawable = R.drawable.about_icon_link
        licenseElement.onClickListener = (View.OnClickListener {
            val intent = Intent(applicationContext, LicenseActivity::class.java)
            startActivity(intent)
        })

        val aboutPage = AboutPage(this)
            .setDescription(getString(R.string.app_name))
            .addItem(versionElement)
            .addItem(authorElement)
            .addItem(emailElement)
            .addItem(webElement)
       //     .addItem(playStoreElement)
            .addGitHub("npes87184")
            .addItem(licenseElement)
            .create()

        setContentView(aboutPage)
    }
}
