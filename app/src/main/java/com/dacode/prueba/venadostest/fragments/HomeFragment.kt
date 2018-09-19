package com.dacode.prueba.venadostest.fragments

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dacode.prueba.venadostest.R
import com.dacode.prueba.venadostest.adapters.PageAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.tab_layout.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val myView = inflater.inflate(R.layout.fragment_home, container, false)

        val tabLayout : TabLayout = myView.tabLayout //mView!!.findViewById(R.id.tabLayout) as TabLayout
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.actmain_tab_name_copa)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.actmain_tab_name_ascenso)))

        val viewPager: ViewPager = myView.viewPager //mView.findViewById(R.id.viewPager) as ViewPager
        val pagerAdapter = PageAdapter(childFragmentManager, tabLayout.tabCount)

        viewPager.adapter = pagerAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(context, "Reselected", Toast.LENGTH_SHORT)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(context, "Unselected", Toast.LENGTH_SHORT)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab!!.position
                viewPager.currentItem = position
            }
        })

        return myView
    }
}
