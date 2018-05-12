package com.npes87184.pokeresearchdictionary.Fragment


import android.os.Bundle
import android.preference.PreferenceFragment
import com.npes87184.pokeresearchdictionary.R

class SettingFragment : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                SettingFragment().apply {

                }
    }
}
