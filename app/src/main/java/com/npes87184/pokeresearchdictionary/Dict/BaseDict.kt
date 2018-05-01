package com.npes87184.pokeresearchdictionary.Dict

import android.content.Context
import android.net.ConnectivityManager
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
import android.widget.TextView

fun timeStampToString(stampSecond: Long): String {
    val date = Date(stampSecond * 1000)
    val cal = Calendar.getInstance()
    cal.time = date
    val year = cal.get(Calendar.YEAR)
    val month = cal.get(Calendar.MONTH)
    val day = cal.get(Calendar.DAY_OF_MONTH)
    val hour = cal.get(Calendar.HOUR_OF_DAY)
    val minute = cal.get(Calendar.MINUTE)
    return "$year/${month+1}/$day $hour:$minute"
}

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

    fun update(textView: TextView) {
        val updater : Updater = Updater(textView, strDictFileName, jsDict!!.version!!, this.context)
        updater.execute(strDictFileName)
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

class Updater(private val textView: TextView, private val strFileName: String, private val currentVersion: Long, private val context: Context?) : AsyncTask<String, Void, String>() {
    private var progressDialog: ProgressDialog? = null

    override fun doInBackground(vararg params : String): String? {
        val cm = context!!.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = cm.activeNetworkInfo
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting) {
            return null
        }
        val strTarget = params[0]
        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://raw.githubusercontent.com/npes87184/PokeResearchDictionary/master/app/src/main/assets/data/$strTarget")
                .build()

        val response = client.newCall(request).execute()
        return response.body()?.string()
    }

    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog = ProgressDialog.show(context,
                context!!.resources.getString(R.string.check_update), context.resources.getString(R.string.checking))
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        progressDialog!!.dismiss()
        val dialog = AlertDialog.Builder(context)
        val gson = Gson()
        val jsDictRet = gson.fromJson(result, DictJson::class.java)

        if (result != null) {
            if (jsDictRet.version!! > currentVersion) {
                val fileJs = File(context!!.filesDir, strFileName)
                fileJs.delete()
                fileJs.writeText(result)
                textView.text = timeStampToString(jsDictRet.version!!)
                dialog.setMessage(R.string.updated)
            } else {
                dialog.setMessage(R.string.current_is_latest)
            }
        } else {
            /* no network */
            dialog.setMessage(R.string.no_network)
        }
        dialog.setPositiveButton(R.string.dict_dialog_ok, { _, _ -> })
        dialog.show()
    }
}