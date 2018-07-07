package com.npes87184.pokeresearchdictionary.Fragment


import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.npes87184.pokeresearchdictionary.R
import com.npes87184.pokeresearchdictionary.Utils.initDict

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class UpdateFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_update, container, false)
        val dict = initDict(context)
        val updateBtn = v.findViewById<Button>(R.id.updateBtn)
        val versionText = v.findViewById<TextView>(R.id.version)
        versionText.text = dict.getVersion()
        dict.update(versionText)

        updateBtn.setOnClickListener({
            dict.update(versionText)
        })

        return v
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
