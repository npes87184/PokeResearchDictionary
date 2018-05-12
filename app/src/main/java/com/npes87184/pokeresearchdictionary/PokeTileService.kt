package com.npes87184.pokeresearchdictionary

import android.preference.PreferenceManager
import android.service.quicksettings.TileService
import com.npes87184.pokeresearchdictionary.Utils.Keys

class PokeTileService : TileService() {
    override fun onClick() {
        super.onClick()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if (prefs.getString(Keys.KEY_PREF_THEME, Keys.KEY_PREF_THEME_DARK) == Keys.KEY_PREF_THEME_DARK) {
            showDialog(DictDialog(this, R.style.AppThemeDark_Dialog))
        } else {
            showDialog(DictDialog(this, R.style.AppThemeLight_Dialog))
        }
    }
}