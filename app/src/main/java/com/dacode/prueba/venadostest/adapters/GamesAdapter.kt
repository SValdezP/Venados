package com.dacode.prueba.venadostest.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.dacode.prueba.venadostest.model.Games
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import com.dacode.prueba.venadostest.R
import com.squareup.picasso.Picasso

class GamesAdapter(games: List<Games>, private var layout: Int, clickListener: OnItemClickListener?) : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    private var games: List<Games>? = games
    private var itemClickListener: OnItemClickListener? = clickListener

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        context = parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Picasso.get(this.context).load(games!![position]).fit().into(holder.imageViewTeam)
        holder.bind(games!![position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return games!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewMonth: TextView = itemView.findViewById(R.id.tViewTabCopaMonth)
        var textViewDate: TextView = itemView.findViewById(R.id.tViewTabCopaDate)
        var textViewDay: TextView = itemView.findViewById(R.id.tViewTabCopaDay)
        var imageViewTeam: ImageView = itemView.findViewById(R.id.imageViewTabCopaTeam)
        var textViewHomeScore: TextView = itemView.findViewById(R.id.tViewTabCopaHomeScore)
        var textViewOpponentScore: TextView = itemView.findViewById(R.id.tViewTabCopaOpponentScore)
        var imageViewOpponent: ImageView = itemView.findViewById(R.id.imageViewTabCopaOpponent)
        var textViewOpponentName: TextView = itemView.findViewById(R.id.tViewTabCopaOpponentName)

        fun bind(games: Games, listener: OnItemClickListener?) {

            textViewMonth.text = games.month
            textViewDate.text = games.dateTime
            textViewDay.text = games.dayTime

            //Picasso.get().load(games.teamImage!!).fit().into(imageViewTeam)

            textViewHomeScore.text = games.homeScore.toString()
            textViewOpponentScore.text = games.awayScore.toString()

            //Picasso.get().load(games.opponentImage!!).fit().into(imageViewOpponent)
            textViewOpponentName.text = games.opponentName

            itemView.setOnClickListener { listener!!.onItemClick(games, adapterPosition) }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(games: Games, position: Int)
    }
}