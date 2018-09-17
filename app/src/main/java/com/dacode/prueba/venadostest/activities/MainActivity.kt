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
import kotlinx.android.synthetic.main.toolbar.*
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dacode.prueba.venadostest.adapters.GamesAdapter
import com.dacode.prueba.venadostest.model.Games

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var games: List<Games>? = null

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: GamesAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val mView : View? = inflate(this, R.layout.coordinator_layout, null)

        val tabLayout : TabLayout = mView!!.findViewById(R.id.tabLayout) as TabLayout
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.actmain_tab_name_copa)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.actmain_tab_name_ascenso)))

        val viewPager: ViewPager = mView.findViewById(R.id.viewPager) as ViewPager
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

        changeFragment(HomeFragment(), nav_view.menu.getItem(0))

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        //----- Recycler View
        games = this.getAllGames()

        val recyclerV : View? = inflate(this, R.layout.fragment_tab_copa_mx, null)

        mRecyclerView = recyclerV!!.findViewById(R.id.recyclerCopaMx) as RecyclerView
        mLayoutManager = LinearLayoutManager(this)

        mAdapter = GamesAdapter(games!!, R.layout.tabs_games, object : GamesAdapter.OnItemClickListener {
            override fun onItemClick(games: Games, position: Int) {
                Toast.makeText(this@MainActivity, "hello", Toast.LENGTH_LONG).show()
            }
        })

        // Use in case size wont change, to improve performance
        mRecyclerView!!.setHasFixedSize(true)

        mRecyclerView!!.itemAnimator = DefaultItemAnimator()

        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mAdapter
    }

    //Temporary function with dummy Data
    private fun getAllGames(): List<Games> {
        return object : ArrayList<Games>() {
            init {
                add(Games("ENERO", "25", "SAB", R.mipmap.ic_team, 3, 1, R.mipmap.ic_opponent, "UNAM", true, "Copa MX"))
                add(Games("FEBRERO", "8", "LUN", R.mipmap.ic_team, 0, 1, R.mipmap.ic_opponent, "Puebla", true, "Copa MX"))
                add(Games("MARZO", "13", "VIER", R.mipmap.ic_team, 2, 2, R.mipmap.ic_opponent, "Celaya F.C.", true, "Copa MX"))
                add(Games("ABRIL", "7", "DOM", R.mipmap.ic_team, 0, 0, R.mipmap.ic_opponent, "Monterrey", true, "Copa MX"))

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
