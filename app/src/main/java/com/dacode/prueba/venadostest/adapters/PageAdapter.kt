package com.dacode.prueba.venadostest.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dacode.prueba.venadostest.fragments.TabAscensoMx
import com.dacode.prueba.venadostest.fragments.TabCopaMx

class PageAdapter(fm: FragmentManager?, private val numberTabs:Int) : FragmentPagerAdapter(fm) {
    
    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> TabCopaMx()
            1 -> TabAscensoMx()
            else -> null
        }
    }

    override fun getCount(): Int {
        return  numberTabs
    }
}