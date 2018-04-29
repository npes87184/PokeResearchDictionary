package com.npes87184.pokeresearchdictionary

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.npes87184.pokeresearchdictionary.Dict.ChtDict
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var str = getString(R.string.all_rules)
        val chtDict = ChtDict()
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val textView = findViewById<TextView>(R.id.allRuleTextView)

        fab.setOnClickListener { _ ->
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        str = "$str\n\n"
        for ((k, v) in chtDict.mapMissionReward) {
            str = "$str* $k: $v\n"
        }
        textView.text = str
    }
}
