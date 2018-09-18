package com.dacode.prueba.venadostest.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.dacode.prueba.venadostest.R
import com.dacode.prueba.venadostest.adapters.PlayersAdapter
import com.dacode.prueba.venadostest.model.Player
import kotlinx.android.synthetic.main.fragment_players.view.*

class PlayersFragment : Fragment() {

    private var player: List<Player>? = null

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: PlayersAdapter? = null
    private var mLayoutManager: GridLayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment_players, container, false)

        player = this.getAllPlayers()

        mRecyclerView = myView.recyclerPlayer as RecyclerView
        mLayoutManager = GridLayoutManager(context, 3)

        mAdapter = PlayersAdapter(player!!, R.layout.model_player, object : PlayersAdapter.OnItemClickListener {
            override fun onItemClick(players: Player, position: Int) {
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
    private fun getAllPlayers(): List<Player> {
        return object : ArrayList<Player>() {
            init {
                add(Player(R.mipmap.ic_mteam, "15", "Carlos Vela"))
                add(Player(R.mipmap.ic_mteam, "10", "Víctor Hugo"))
                add(Player(R.mipmap.ic_mteam, "16", "Víctor Manuel"))
                add(Player(R.mipmap.ic_mteam, "17", "Jahir Alejandro"))
                add(Player(R.mipmap.ic_mteam, "28", "Ían de Jesús"))
                add(Player(R.mipmap.ic_mteam, "15", "Esteban"))
            }
        }
    }

}
