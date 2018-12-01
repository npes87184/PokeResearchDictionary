package com.npes87184.pokeresearchdictionary.Utils

import android.content.Context
import android.os.LocaleList
import android.preference.PreferenceManager
import com.npes87184.pokeresearchdictionary.Dict.BaseDict
import com.npes87184.pokeresearchdictionary.Dict.ChtDict
import com.npes87184.pokeresearchdictionary.Dict.EnuDict
import java.util.*


fun initDict(context: Context): BaseDict {
    val locale = LocaleList.getDefault().get(0)
    var strLang = locale.language + "-" + locale.country
    var retDict: BaseDict? = null
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    val strPrefs = prefs.getString(Keys.KEY_PREF_DICT_LANGUAGE, Keys.KEY_PREF_DICT_LANGUAGE_DEFAULT)

    if (strPrefs != Keys.KEY_PREF_DICT_LANGUAGE_DEFAULT) {
        strLang = strPrefs
    }

    retDict = if (strLang == Keys.KEY_PREF_DICT_LANGUAGE_ZH_TW ||
            strLang == Keys.KEY_PREF_DICT_LANGUAGE_ZH_HK) {
        ChtDict()
    } else {
        /* If we don't know which dict should use, use Enu. */
        EnuDict()
    }
    retDict?.setUp(context)

    return retDict!!
}

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