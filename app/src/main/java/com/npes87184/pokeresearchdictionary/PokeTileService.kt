package com.npes87184.pokeresearchdictionary

import android.service.quicksettings.TileService

class PokeTileService : TileService() {
    override fun onClick() {
        super.onClick()
        showDialog(DictDialog(this))
    }
}