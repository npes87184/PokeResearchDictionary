package com.npes87184.pokeresearchdictionary

import android.app.AlertDialog
import android.service.quicksettings.TileService
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import com.npes87184.pokeresearchdictionary.Dict.ChtDict

class PokeTileService : TileService() {
    override fun onClick() {
        super.onClick()
        val chtDict = ChtDict()
        val inflater = LayoutInflater.from(this)
        val layout = inflater.inflate(R.layout.dict_dialog_layout, null)
        val editText = layout.findViewById<EditText>(R.id.editText)
        val textView = layout.findViewById<TextView>(R.id.textView)

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val retMap = chtDict.search(editText.text.toString())
                var str = String()

                if (editText.text.toString().isNotEmpty() && !retMap.isEmpty()) {
                    for ((k, v) in retMap) {
                        str = "$str* $k: $v\n"
                    }
                } else {
                    str = getString(R.string.no_result)
                }
                textView.text = str
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        val builder = AlertDialog.Builder(this)

        builder.setView(layout)
               .setPositiveButton(R.string.dict_dialog_cancel, { _, _ -> })
        showDialog(builder.create())
    }
}