package com.dacode.prueba.venadostest.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View.inflate
import android.widget.Toast
import com.dacode.prueba.venadostest.R
import com.dacode.prueba.venadostest.adapters.PageAdapter
import com.dacode.prueba.venadostest.fragments.HomeFragment
import com.dacode.prueba.venadostest.fragments.PlayersFragment
import com.dacode.prueba.venadostest.fragments.StatisticsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.coordinator_layout.view.*
import kotlinx.android.synthetic.main.tab_layout.view.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val view = inflate(this, R.layout.fragment_home, null)

        val tabLayout : TabLayout = view.tabLayout
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.actmain_tab_name_copa)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.actmain_tab_name_ascenso)))

        val viewPager: ViewPager = view.viewPager
        val pagerAdapter = PageAdapter(supportFragmentManager, tabLayout.tabCount)

        viewPager.adapter = pagerAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity, "Reselected", Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity, "Unselected", Toast.LENGTH_SHORT).show()
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab!!.position
                viewPager.currentItem = position
            }

        })

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

    fun changeFragment(fragment: Fragment?, item: MenuItem){

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.contentFrameLayout, fragment)
                .commit()

        supportActionBar!!.title = item.title
    }
}
