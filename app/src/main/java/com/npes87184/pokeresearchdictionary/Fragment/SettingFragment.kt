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
    private var dictLanguagePref : Preference? = null

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

        dictLanguagePref = findPreference(Keys.KEY_PREF_DICT_LANGUAGE)
        when (prefs!!.getString(Keys.KEY_PREF_DICT_LANGUAGE, Keys.KEY_PREF_DICT_LANGUAGE_DEFAULT)) {
            Keys.KEY_PREF_DICT_LANGUAGE_DEFAULT -> dictLanguagePref!!.summary = getString(R.string.pref_system_default)
            Keys.KEY_PREF_DICT_LANGUAGE_ZH_TW -> dictLanguagePref!!.summary = getString(R.string.pref_dict_language_cht)
            else -> dictLanguagePref!!.summary = getString(R.string.pref_dict_language_enu)
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (!isAdded) {
            /* Prevent fragment not attached to activity after restartActivity() */
            return
        }
        when (key) {
            Keys.KEY_PREF_THEME -> {
                if (prefs!!.getString(Keys.KEY_PREF_THEME, Keys.KEY_PREF_THEME_LIGHT) == Keys.KEY_PREF_THEME_LIGHT) {
                    themePref!!.summary = "Material Light"
                } else {
                    themePref!!.summary = "Material Dark"
                }
                restartActivity()
            }
            Keys.KEY_PREF_DICT_LANGUAGE -> {
                when (prefs!!.getString(Keys.KEY_PREF_DICT_LANGUAGE, Keys.KEY_PREF_DICT_LANGUAGE_DEFAULT)) {
                    Keys.KEY_PREF_DICT_LANGUAGE_DEFAULT -> dictLanguagePref!!.summary = getString(R.string.pref_system_default)
                    Keys.KEY_PREF_DICT_LANGUAGE_ZH_TW -> dictLanguagePref!!.summary = getString(R.string.pref_dict_language_cht)
                    else -> dictLanguagePref!!.summary = getString(R.string.pref_dict_language_enu)
                }
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
