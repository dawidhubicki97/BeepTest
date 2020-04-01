package com.example.beeptest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_score_window.*

class scoreWindow : AppCompatActivity() {
    var dystans: Int=0
    var poziom: Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_window)
        dystans =intent.getIntExtra("dystans",0)
        poziom=intent.getIntExtra("poziom",0)
        dystansWyswietl.setText("Distance: "+dystans.toString())
        poziomWyswietl.setText("Level: "+poziom.toString())
        scoreWyswietl.setText(scoreRating())
    }

    fun scoreRating(): String{
        when(poziom) {
            in 1..4-> return "very poor"
            in 5..7-> return "poor"
            in 7..9-> return "average"
            in 9..11->return "good"
            in 11..13->return "very good"
            else ->return "excellent"

        }
    }
}


