package com.dacode.prueba.venadostest.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dacode.prueba.venadostest.R
import kotlinx.android.synthetic.main.activity_dummy_player_details.*

class DummyPlayerDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy_player_details)

        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val bundle: Bundle = intent.extras
        val image = bundle.getInt("image")
        val position = bundle.getString("position")
        val name = bundle.getString("name")

        imageViewDetailPlayer.setImageResource(image)
        tViewDetailPosition.text = position
        tViewDetailPlayerName.text = name
    }
}
