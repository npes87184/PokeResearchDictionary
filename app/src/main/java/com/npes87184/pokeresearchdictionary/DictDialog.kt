package com.npes87184.pokeresearchdictionary

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.npes87184.pokeresearchdictionary.Utils.initDict
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.view.View
import com.npes87184.pokeresearchdictionary.Utils.Keys


class DictDialog(context: Context) : Dialog(context, R.style.AppTheme_Dialog) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dict_dialog_layout)

        val dict = initDict(context)

        val editText = findViewById<EditText>(R.id.editText)
        val textView = findViewById<TextView>(R.id.content)
        val updateBtn = findViewById<Button>(R.id.buttonUpdate)
        val cancelBtn = findViewById<Button>(R.id.buttonEnd)

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val retMap = dict.search(editText.text.toString())
                var str = String()

                if (editText.text.toString().isNotEmpty() && !retMap.isEmpty()) {
                    for ((k, v) in retMap) {
                        str = "$str* $k: $v\n"
                    }
                    updateBtn.visibility = View.INVISIBLE
                } else {
                    str = context.getString(R.string.no_result)
                    updateBtn.visibility = View.VISIBLE
                }
                textView.text = str
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        textView.movementMethod = ScrollingMovementMethod()

        updateBtn.setOnClickListener({
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(Keys.KEY_START_FRAGMENT, R.id.nav_update)
            context.startActivity(intent)

            this.dismiss()
        })

        cancelBtn.setOnClickListener({
            this.dismiss()
        })
    }
}