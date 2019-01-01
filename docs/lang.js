function GetLan() {
    var userLang = navigator.language || navigator.userLanguage;
    if (userLang === "zh-TW") {
        return "cht";
    }
    return "enu";
}

function GetStr(key, def, lang) {
    if (lang === undefined) {
        lang = GetLan();
    }
    var retStr = eval('eval(lang).' + key);

    if (typeof retStr != 'undefined') {
        return retStr;
    } else {
        if (typeof defaultStr != 'undefined') {
            return defaultStr;
        } else {
            return eval('enu.' + key);
        }
    }
}
