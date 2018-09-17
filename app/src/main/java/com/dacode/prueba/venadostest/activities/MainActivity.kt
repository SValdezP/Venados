package com.dacode.prueba.venadostest.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.dacode.prueba.venadostest.R
import com.dacode.prueba.venadostest.fragments.HomeFragment
import com.dacode.prueba.venadostest.fragments.PlayersFragment
import com.dacode.prueba.venadostest.fragments.StatisticsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        changeFragment(HomeFragment(), nav_view.menu.getItem(0))

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        var flag = false
        var fragment : Fragment? = null

        when (item.itemId) {

            R.id.menu_drawer_home -> {
                fragment = HomeFragment()
                flag = true
            }

            R.id.menu_drawer_statistics -> {
                fragment = StatisticsFragment()
                flag = true
            }

            R.id.menu_drawer_players -> {
                fragment = PlayersFragment()
                flag = true
            }
        }

        if (flag) {
            changeFragment(fragment, item)
            drawer_layout.closeDrawers()
        }

        return true
    }

    private fun changeFragment(fragment: Fragment?, item: MenuItem){

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.contentFrameLayout, fragment)
                .commit()

        supportActionBar!!.title = item.title
    }
}
