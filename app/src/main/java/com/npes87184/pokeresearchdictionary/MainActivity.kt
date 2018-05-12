package com.npes87184.pokeresearchdictionary

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.npes87184.pokeresearchdictionary.Fragment.ResearchListFragment
import com.npes87184.pokeresearchdictionary.Fragment.SettingFragment
import com.npes87184.pokeresearchdictionary.Fragment.UpdateFragment
import com.npes87184.pokeresearchdictionary.Utils.Keys
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if (prefs.getString(Keys.KEY_PREF_THEME, Keys.KEY_PREF_THEME_DARK) == Keys.KEY_PREF_THEME_DARK) {
            setTheme(R.style.AppThemeDark)
        } else {
            setTheme(R.style.AppThemeLight)
        }
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val startFragmentId = intent.getIntExtra(Keys.KEY_START_FRAGMENT, R.id.nav_all_list)

        val fm = fragmentManager
        val ft = fm.beginTransaction()
        when (startFragmentId) {
            R.id.nav_update -> {
                ft.replace(R.id.container, UpdateFragment.newInstance())
                ft.commitNow()
            }
            R.id.nav_setting -> {
                ft.replace(R.id.container, SettingFragment.newInstance())
                ft.commitNow()
            }
            else -> {
                ft.replace(R.id.container, ResearchListFragment.newInstance())
                ft.commitNow()
            }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fm = fragmentManager
        val ft = fm.beginTransaction()

        when (item.itemId) {
            R.id.nav_all_list -> {
                ft.replace(R.id.container, ResearchListFragment.newInstance())
                ft.commit()
            }
            R.id.nav_update -> {
                ft.replace(R.id.container, UpdateFragment.newInstance())
                ft.commit()
            }
            R.id.nav_setting -> {
                ft.replace(R.id.container, SettingFragment.newInstance())
                ft.commit()
            }
            R.id.nav_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_share -> {
                val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                val shareBody = "https://play.google.com/store/apps/details?id=com.npes87184.pokeresearchdictionary"
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name))
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)))
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
