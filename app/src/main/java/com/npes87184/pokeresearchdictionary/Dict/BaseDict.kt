package com.npes87184.pokeresearchdictionary.Dict

import android.content.Context
import android.os.AsyncTask
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.util.*
import android.app.ProgressDialog
import com.npes87184.pokeresearchdictionary.R
import android.app.AlertDialog
import android.view.View
import com.npes87184.pokeresearchdictionary.Utils.timeStampToString
import kotlin.collections.ArrayList

abstract class BaseDict {
    abstract var strDictFileName : String
    var jsDict : DictJson? = null
    var filesDir : File? = null
    var context : Context? = null

    init {

    }

    private fun readDict(fileJs: File): DictJson {
        var gson = Gson()
        val bufferedReader: BufferedReader = fileJs.bufferedReader()
        val strJson = bufferedReader.use { it.readText() }
        bufferedReader.close()
        return gson.fromJson(strJson, DictJson::class.java)
    }

    fun setUp(context: Context) {
        this.context = context
        filesDir = context.filesDir
        val fileJs = File(context.filesDir, strDictFileName)
        val fileTmpJs = File(context.filesDir, "tmp.json")

        if (!fileJs.exists()) {
            context.resources.assets.open("data/$strDictFileName").copyTo(FileOutputStream(fileJs.path))
        } else {
            context.resources.assets.open("data/$strDictFileName").copyTo(FileOutputStream(fileTmpJs.path))
            val jsCurrDict = readDict(fileJs)
            val jsPkgDict = readDict(fileTmpJs)

            if (jsPkgDict.version!! > jsCurrDict.version!!) {
                fileJs.delete()
                context.resources.assets.open("data/$strDictFileName").copyTo(FileOutputStream(fileJs.path))
            }
            fileTmpJs.delete()
        }
        jsDict = readDict(fileJs)
    }

    fun getVersion(): String {
        return timeStampToString(jsDict!!.version!!)
    }

    fun getListNum(): String {
        return jsDict!!.data!!.size.toString()
    }

    fun fetch(listener: Fetcher.UpdaterListener) {
        val fetcher = Fetcher()
        fetcher.setListener(listener)
        fetcher.execute(strDictFileName)
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

class Fetcher : AsyncTask<String, Void, String>() {
    private var listener: UpdaterListener? = null

    interface UpdaterListener {
        fun onFetchFinished(strResult: String)
    }

    fun setListener(listener: UpdaterListener) {
        this.listener = listener
    }

    override fun doInBackground(vararg params : String): String? {
        val strTarget = params[0]
        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://raw.githubusercontent.com/npes87184/PokeResearchDictionary/master/app/src/main/assets/data/$strTarget")
                .build()

        return try {
            val response = client.newCall(request).execute()
            response.body()?.string()
        } catch (e: Exception) {
            ""
        }
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        this.listener!!.onFetchFinished(result!!)
    }
}