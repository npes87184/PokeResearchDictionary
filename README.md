PokeResearchDictionary
===
Search the Pokemon Go research reward quickly from tile.

This app uses the quick setting tile api from android version 7 to create a quick shortcut, which can be easily accessed in game. After click it, user can directly search the reward from dialog.

Note: Only support Traditional Chinese and English now.

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
* 1.2.0

# Changelog
## 1.2.0
* Add theme support
* Add update button in DictDialog

## 1.1.1
* Add enu support.
* Update internal database.

## 1.1.0
* Dramatically improve the UI, we can start search dialog from app now.
* Support online update database.

## 1.0.0
* Initial release

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
3. Edit `PokeResearchDictionary/app/src/main/java/com/npes87184/pokeresearchdictionary/Utils/Utils.kt` to support your dictionary.
Thats it!

You can follow [this commit](https://github.com/npes87184/PokeResearchDictionary/commit/6f09721c5ce599a9e49b884dbcf157ec60916321) for more information.

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
