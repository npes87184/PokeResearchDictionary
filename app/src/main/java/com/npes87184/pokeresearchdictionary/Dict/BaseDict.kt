package com.npes87184.pokeresearchdictionary.Dict

import android.content.Context
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream

abstract class BaseDict {
    abstract var strDictFileName : String
    var jsDict : DictJson? = null
    var filesDir : File? = null

    init {

    }

    private fun readDict(fileJs: File) {
        var gson = Gson()
        val bufferedReader: BufferedReader = fileJs.bufferedReader()
        val strJson = bufferedReader.use { it.readText() }
        jsDict = gson.fromJson(strJson, DictJson::class.java)
        bufferedReader.close()
    }

    fun setUp(context: Context) {
        filesDir = context.filesDir
        val fileJs = File(context.filesDir, strDictFileName)

        if (!fileJs.exists()) {
            context.resources.assets.open("data/$strDictFileName").copyTo(FileOutputStream(fileJs.path))
        }
        readDict(fileJs)
    }

    fun search(key : String) : MutableMap<String, String> {
        var ret : MutableMap<String, String> = mutableMapOf()

        for (p in jsDict?.data!!.iterator()) {
            if (p.key!!.contains(key, true)) {
                ret[p.key] = p.value!!
            }
        }
        return ret
    }
}