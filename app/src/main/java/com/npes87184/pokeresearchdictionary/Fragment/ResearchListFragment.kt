package com.npes87184.pokeresearchdictionary.Fragment

import android.os.Bundle
import android.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.npes87184.pokeresearchdictionary.Dict.ChtDict

import com.npes87184.pokeresearchdictionary.R
import android.support.design.widget.FloatingActionButton
import com.npes87184.pokeresearchdictionary.DictDialog

class ResearchListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_research_list, container, false)
        val textView = v.findViewById<TextView>(R.id.textView)
        val fab = v.findViewById(R.id.fab) as FloatingActionButton
        var str = getString(R.string.all_rules)
        val chtDict = ChtDict()

        str = "$str\n\n"
        for ((k, v) in chtDict.mapMissionReward) {
            str = "$str* $k: $v\n"
        }
        textView.text = str
        textView.movementMethod = ScrollingMovementMethod()

        fab.setOnClickListener { _ ->
            DictDialog(context).show()
        }

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        @JvmStatic
        fun newInstance() =
                ResearchListFragment().apply {
                }
    }
}
