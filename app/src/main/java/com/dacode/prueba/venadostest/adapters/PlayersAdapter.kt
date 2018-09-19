package com.dacode.prueba.venadostest.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dacode.prueba.venadostest.R
import com.dacode.prueba.venadostest.activities.DummyPlayerDetailsActivity
import com.dacode.prueba.venadostest.model.Player
import com.squareup.picasso.Picasso

class PlayersAdapter(players: List<Player>, private var layout: Int, clickListener: OnItemClickListener?) : RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {

    private var player: List<Player>? = players
    private var itemClickListener: OnItemClickListener? = clickListener

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        context = parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(player!![position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return player!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageViewPlayer: ImageView = itemView.findViewById(R.id.imageViewPlayer)
        var textViewPosition: TextView = itemView.findViewById(R.id.tViewPosition)
        var textViewName: TextView = itemView.findViewById(R.id.tViewPlayerName)

        fun bind(players: Player, listener: OnItemClickListener?) {

            Picasso.get().load(players.image!!).fit().into(imageViewPlayer)
            textViewPosition.text = players.position
            textViewName.text = players.name

            imageViewPlayer.setOnClickListener {
                val intent = Intent(context, DummyPlayerDetailsActivity::class.java)
                intent.putExtra("image", players.image!!)
                intent.putExtra("position", players.position!!)
                intent.putExtra("name", players.name!!)
                context!!.startActivity(intent)
            }

            itemView.setOnClickListener { listener!!.onItemClick(players, adapterPosition) }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(players: Player, position: Int)
    }
}