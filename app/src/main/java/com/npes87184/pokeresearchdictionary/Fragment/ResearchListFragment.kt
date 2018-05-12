package com.npes87184.pokeresearchdictionary.Fragment

import android.os.Bundle
import android.app.Fragment
import android.preference.PreferenceManager
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.npes87184.pokeresearchdictionary.R
import android.support.design.widget.FloatingActionButton
import com.npes87184.pokeresearchdictionary.DictDialog
import com.npes87184.pokeresearchdictionary.Utils.Keys
import com.npes87184.pokeresearchdictionary.Utils.initDict

class ResearchListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_research_list, container, false)
        val textView = v.findViewById<TextView>(R.id.content)
        val fab = v.findViewById(R.id.fab) as FloatingActionButton
        var str = getString(R.string.all_rules)
        val dict = initDict(context)
        val prefs = PreferenceManager.getDefaultSharedPreferences(activity)

        str = "$str\n\n"
        for (p in dict.jsDict?.data!!.iterator()) {
            str = "$str* ${p.key}: ${p.value}\n"
        }
        textView.text = str
        textView.movementMethod = ScrollingMovementMethod()

        fab.setOnClickListener { _ ->
            if (prefs.getString(Keys.KEY_PREF_THEME, Keys.KEY_PREF_THEME_LIGHT) == Keys.KEY_PREF_THEME_LIGHT) {
                DictDialog(context, R.style.AppThemeLight_Dialog).show()
            } else {
                DictDialog(context, R.style.AppThemeDark_Dialog).show()
            }
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
                ResearchListFragment()
    }
}
