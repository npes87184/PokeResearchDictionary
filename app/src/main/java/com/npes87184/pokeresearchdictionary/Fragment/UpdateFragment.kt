package com.npes87184.pokeresearchdictionary.Fragment


import android.app.AlertDialog
import android.app.Fragment
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.npes87184.pokeresearchdictionary.Dict.BaseDict
import com.npes87184.pokeresearchdictionary.Dict.DictJson
import com.npes87184.pokeresearchdictionary.Dict.Fetcher
import com.npes87184.pokeresearchdictionary.MainActivity
import com.npes87184.pokeresearchdictionary.R
import com.npes87184.pokeresearchdictionary.Utils.initDict
import com.npes87184.pokeresearchdictionary.Utils.timeStampToString
import java.io.File
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [UpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class UpdateFragment : Fragment(), Fetcher.UpdaterListener {
    var dict : BaseDict? = null
    private var progressDialog: ProgressDialog? = null
    private var img: ImageView? = null
    private var versionText: TextView? = null
    private var listNumText: TextView? = null
    private var dictStateText: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_update, container, false)
        val linearLayoutUpdate = v.findViewById(R.id.linearLayoutUpdate) as LinearLayout
        val linearLayoutReport = v.findViewById(R.id.linearLayoutReport) as LinearLayout
        val linearLayoutDonate = v.findViewById(R.id.linearLayoutDonate) as LinearLayout
        val attachedActivity = activity as MainActivity

        dict = initDict(context)
        versionText = v.findViewById<TextView>(R.id.textViewVersion)
        listNumText = v.findViewById<TextView>(R.id.textViewListNum)
        dictStateText = v.findViewById<TextView>(R.id.textViewDictState)
        img = v.findViewById<ImageView>(R.id.imageView2)

        versionText!!.text = dict!!.getVersion()
        listNumText!!.text = dict!!.getListNum()
        linearLayoutUpdate.setOnClickListener {
            doUpdate()
        }

        linearLayoutReport.setOnClickListener {
            val urlIntent = Intent(Intent.ACTION_VIEW)
            urlIntent.data = Uri.parse("https://npes87184.github.io/PokeResearchDictionary/report.html")
            startActivity(urlIntent)
        }

        linearLayoutDonate.setOnClickListener {
            attachedActivity.billingManager?.launchPurchaseFlow(attachedActivity.DONATE_COFFEE_SKU_ID)
        }

        doUpdate()
        return v
    }

    private fun doUpdate() {
        if (hasNetwork()) {
            progressDialog = ProgressDialog.show(activity,
                    getString(R.string.check_update), getString(R.string.checking))
            dict!!.fetch(this)
        } else {
            dictStateText!!.text = getString(R.string.no_network)
            img!!.setImageResource(R.drawable.pikachu)
        }
    }

    private fun hasNetwork() : Boolean {
        val cm = context!!.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = cm.activeNetworkInfo
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting) {
            return false
        }
        return true
    }

    override fun onFetchFinished(strResult : String) {
        progressDialog!!.dismiss()

        if (strResult.isEmpty()) {
            return
        }

        val dialog = AlertDialog.Builder(context)
        dialog.setPositiveButton(R.string.dict_dialog_ok, { _, _ -> })

        val gson = Gson()
        val jsDictRet = gson.fromJson(strResult, DictJson::class.java)

        if (jsDictRet.version!! > dict!!.jsDict!!.version!!) {
            /* save new file */
            val fileJs = File(context!!.filesDir, dict!!.strDictFileName)
            fileJs.delete()
            fileJs.writeText(strResult)
            /* Set latest version and new list number */
            versionText!!.text = timeStampToString(jsDictRet.version!!)
            listNumText!!.text = jsDictRet.data!!.size.toString()
            /* Get different entry */
            var list = dictMinus(jsDictRet, dict!!.jsDict!!)
            dialog.setMessage(context.getString(R.string.updated) + dictListToString((list)))
            dialog.show()
        }
        dictStateText!!.text = getString(R.string.you_are_up_to_date)
        img!!.setImageResource(R.drawable.insignia)
    }

    private fun dictMinus(jsDictA: DictJson, jsDictB: DictJson): ArrayList<DictJson.Pair> {
        var list: ArrayList<DictJson.Pair> = ArrayList()

        for (pA in jsDictA?.data!!.iterator()) {
            var blFind = false

            for (pB in jsDictB?.data!!.iterator()) {
                if (pA.key == pB.key && pA.value == pB.value) {
                    blFind = true
                    break
                }
            }

            if (!blFind) {
                list.add(pA)
            }
        }

        return list
    }

    private fun dictListToString(list: ArrayList<DictJson.Pair>): String {
        var strRet: String = String()

        for (p in list) {
            strRet = "$strRet* ${p.key}: ${p.value}\n"
        }

        return strRet
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment UpdateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                UpdateFragment()
    }
}
