package com.npes87184.pokeresearchdictionary.Utils

import android.content.Context
import android.os.LocaleList
import com.npes87184.pokeresearchdictionary.Dict.BaseDict
import com.npes87184.pokeresearchdictionary.Dict.ChtDict
import com.npes87184.pokeresearchdictionary.Dict.EnuDict



fun initDict(context: Context): BaseDict {
    val locale = LocaleList.getDefault().get(0)
    val strLang = locale.language + "-" + locale.country
    var retDict: BaseDict? = null

    if (strLang.equals("zh-TW") || strLang.equals("zh-HK")) {
        retDict = ChtDict()
    } else {
        /* If we don't know which dict should use, use Enu. */
        retDict = EnuDict()
    }
    retDict?.setUp(context)

    return retDict!!
}