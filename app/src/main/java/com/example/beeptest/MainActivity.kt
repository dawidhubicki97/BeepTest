package com.example.beeptest

import android.content.Intent
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.os.CountDownTimer
import android.support.annotation.RequiresApi
import android.widget.Button
import java.text.DateFormat

import java.util.*

class MainActivity : AppCompatActivity() {
    val levels =arrayListOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)
    val shuttlePerLevel =arrayListOf(7,8,8,9,9,10,10,11,11,11,12,12,13,13,13)
    val levelTime=arrayListOf(9000,8000,7580,7200,6860,6550,6260,6000,5760,5540,5330,5140,4970,4800,4650)
    var counter=0
    var shuttle=1
    var works=false
    var distance=0
    private lateinit var mp: MediaPlayer
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context=this
        setContentView(R.layout.activity_main)
        val startButton = findViewById(R.id.startButton) as Button
        val helpButton = findViewById(R.id.helpButton) as Button
        val historyButton = findViewById(R.id.historyButton) as Button
        startButton.setOnClickListener {
            if(works==false) {

                works=true
                shuttle=1
                counter=0
                distance=0
                myTimer()
                startButton.setText("STOP")
            }
            else{
                startScoreWindow()
                var calendar = Calendar.getInstance()
                var current = DateFormat.getDateInstance().format(calendar.getTime())

                var scoresDatabase=scoresDatabase(1,current.toString(),levels[counter],shuttle,distance)
                var db=myDBHandler(context)
                db.insertData(scoresDatabase)
                works=false
                startButton.setText("Start")
                levelShow.setText("Level: "+0.toString())
                shuttleShow.setText("Shuttle: "+0.toString())
                showTime.setText(0.toString())
                distanceShow.setText("Distance: "+0.toString())
            }
        }
        helpButton.setOnClickListener {
            val intent = Intent(this, instructionActivity::class.java)
            startActivity(intent)
        }
        historyButton.setOnClickListener {
            val intent = Intent(this, historyActivity::class.java)
            startActivity(intent)
        }

    }
    fun myTimer()
    {
        if(works==false){
            return
        }
        mp= MediaPlayer.create(this,R.raw.beep)
        distanceShow.setText("Distance: "+distance.toString() + "m")
        levelShow.setText("Level: "+levels[counter].toString())
        shuttleShow.setText("Shuttle: "+shuttle.toString())
        object : CountDownTimer(levelTime[counter].toLong(), 10) {

            override fun onTick(millisUntilFinished: Long) {
                showTime.setText(""+ millisUntilFinished / 1000+","+millisUntilFinished %1000)
                if(works==false){
                    cancel()
                    return
                }
            }

            override fun onFinish() {


                    shuttle++
                    distance=distance+20
                    if (shuttle > shuttlePerLevel[counter]) {
                        mp.start()
                        shuttle = 1
                        counter++
                        myTimer()
                    } else {
                        mp.start()
                        myTimer()
                    }
                }


        }.start()

    }
    fun startScoreWindow(){
        val intent = Intent(this, scoreWindow::class.java)
        intent.putExtra("poziom",levels[counter] )
        intent.putExtra("dystans",distance)
        startActivity(intent)
    }
}
