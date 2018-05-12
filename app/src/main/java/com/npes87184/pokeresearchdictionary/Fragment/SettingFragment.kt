package com.npes87184.pokeresearchdictionary.Fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import com.npes87184.pokeresearchdictionary.R
import com.npes87184.pokeresearchdictionary.Utils.Keys
import android.content.Intent


class SettingFragment : PreferenceFragment(), SharedPreferences.OnSharedPreferenceChangeListener {
    private var prefs : SharedPreferences? = null
    private var themePref : Preference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)

        prefs = preferenceManager.sharedPreferences
        prefs!!.registerOnSharedPreferenceChangeListener(this)

        themePref = findPreference(Keys.KEY_PREF_THEME)
        if (prefs!!.getString(Keys.KEY_PREF_THEME, Keys.KEY_PREF_THEME_LIGHT) == Keys.KEY_PREF_THEME_LIGHT) {
            themePref!!.summary = "Material Light"
        } else {
            themePref!!.summary = "Material Dark"
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            Keys.KEY_PREF_THEME -> {
                if (prefs!!.getString(Keys.KEY_PREF_THEME, Keys.KEY_PREF_THEME_LIGHT) == Keys.KEY_PREF_THEME_LIGHT) {
                    themePref!!.summary = "Material Light"
                } else {
                    themePref!!.summary = "Material Dark"
                }
                if (!isAdded) {
                    return
                }
                restartActivity()
            }
        }
    }

    private fun restartActivity() {
        val intent = activity.intent
        intent.putExtra(Keys.KEY_START_FRAGMENT, R.id.nav_setting)

        activity.overridePendingTransition(0, 0)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        activity.finish()
        activity.overridePendingTransition(0, 0)
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                SettingFragment().apply {

                }
    }
}
