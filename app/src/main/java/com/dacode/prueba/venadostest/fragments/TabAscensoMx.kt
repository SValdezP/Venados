package com.dacode.prueba.venadostest.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.dacode.prueba.venadostest.R
import com.dacode.prueba.venadostest.adapters.GamesAdapter
import com.dacode.prueba.venadostest.model.Games
import kotlinx.android.synthetic.main.fragment_tab_ascenso_mx.view.*

class TabAscensoMx : Fragment() {

    private var games: List<Games>? = null

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: GamesAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val myView = inflater.inflate(R.layout.fragment_tab_ascenso_mx, container, false)

        games = this.getAllGames()

        mRecyclerView = myView.recyclerAscensoMx as RecyclerView
        mLayoutManager = LinearLayoutManager(context)

        mAdapter = GamesAdapter(games!!, R.layout.tabs_games, object : GamesAdapter.OnItemClickListener {
            override fun onItemClick(games: Games, position: Int) {
                Toast.makeText(context, "hello", Toast.LENGTH_LONG).show()
            }
        })

        // Use in case size wont change, to improve performance
        mRecyclerView!!.setHasFixedSize(true)

        mRecyclerView!!.itemAnimator = DefaultItemAnimator()

        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mAdapter

        return myView
    }

    //Temporary function with dummy Data
    private fun getAllGames(): List<Games> {
        return object : ArrayList<Games>() {
            init {
                add(Games("ENERO", "25", "SAB", R.mipmap.ic_mteam, 0, 1, R.mipmap.ic_opponent, "TM Fútbol Club", true, "Ascenso MX"))
                add(Games("ENERO", "25", "SAB", R.mipmap.ic_mteam, 3, 1, R.mipmap.ic_opponent, "Club Atlético Zacatepec ", true, "Ascenso MX"))
                add(Games("ENERO", "25", "SAB", R.mipmap.ic_mteam, 3, 1, R.mipmap.ic_opponent, "Club Atlético de San Luis ", true, "Ascenso MX"))
                add(Games("ENERO", "25", "SAB", R.mipmap.ic_mteam, 4, 3, R.mipmap.ic_opponent, "Celaya F.C.", true, "Ascenso MX"))
            }
        }
    }
}
