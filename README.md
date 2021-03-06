PokeResearchDictionary
===
Search the Pokemon Go research reward quickly from tile.

This app uses the quick setting tile api from android version 7 to create a quick shortcut, which can be easily accessed in game. After click it, user can directly search the reward from dialog.

Note: Only support Traditional Chinese and English now.

<a href="https://play.google.com/store/apps/details?id=com.npes87184.pokeresearchdictionary">
    <img src="https://play.google.com/intl/en_gb/badges/images/generic/en_badge_web_generic.png" width="200">
</a>

It also has a web interface for reporting and listing research: [PokeResearchDictionary](https://npes87184.github.io/PokeResearchDictionary/list.html)

# Features
* Quickly start from tile.
* Instantly filter the database and display the results when the input ends.
* No login, no internet connection, you can check it at any time.
* Support online update database.
* Does not require unreasonable permission, light and small without advertising.
* Open source

# Pictures
<img src="https://raw.github.com/npes87184/PokeResearchDictionary/master/img/1.png" width="450">

<img src="https://raw.github.com/npes87184/PokeResearchDictionary/master/img/2.png" width="450">

# Latest Version
* 1.4.0

# Changelog
## 1.4.0
* Redesign update fragment
* Add app icon in the dict dialog

## 1.3.1
* Support reporting research
* Always show update button
* Add donation button

# Contributions
## Add dictionary of your own language

1. Add your dictionary in `PokeResearchDictionary/app/src/main/assets/data/XXX.json`, you can take enu.json as reference.
2. Add dict.kt in `PokeResearchDictionary/app/src/main/java/com/npes87184/pokeresearchdictionary/Dict/XXXDict.kt` in this file
it should look like this:
```
package com.npes87184.pokeresearchdictionary.Dict

class XXXDict : BaseDict() {
    override var strDictFileName: String = "XXX.json"

    init {
    }
}
```
3. Edit `app/src/main/res/values/arrays.xml` and `app/src/main/res/values/strings.xml` to support setting dictionary language.

4. Edit `PokeResearchDictionary/app/src/main/java/com/npes87184/pokeresearchdictionary/Utils/Utils.kt` to support your dictionary.

**Thats it!**

You can follow [this commit](https://github.com/npes87184/PokeResearchDictionary/commit/6f09721c5ce599a9e49b884dbcf157ec60916321) and [this commit](https://github.com/npes87184/PokeResearchDictionary/commit/10c8d762a97f0b9c41d38fa9468915764ec8747a) for more information.

## Update dictionary
Directly edit the json in `PokeResearchDictionary/app/src/main/assets/data/`. After your PR is merged, user can update dictionary directly from app.

## Other great work

You can do everything you want and send a PR or help us fix [issues](https://github.com/npes87184/PokeResearchDictionary/issues). Any help is welcome.

# Thanks
* [Roundicons Freebies](https://www.flaticon.com/authors/roundicons-freebies) for icon
* [android-about-page](https://github.com/medyo/android-about-page)
* [bluelamb at ptt](https://www.ptt.cc/bbs/PokemonGO/M.1522472393.A.35E.html)

# License
```
MIT License

Copyright (c) 2018 npes87184

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
