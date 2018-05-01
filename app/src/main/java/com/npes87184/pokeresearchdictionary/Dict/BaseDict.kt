package com.npes87184.pokeresearchdictionary.Dict

abstract class BaseDict {
    abstract val mapMissionReward : Map<String, String>

    init {

    }

    fun search(key : String) : MutableMap<String, String> {
        var ret : MutableMap<String, String> = mutableMapOf()

        for ((k, v) in mapMissionReward) {
            if (k.contains(key, true)) {
                ret[k] = v
            }
        }
        return ret
    }
}