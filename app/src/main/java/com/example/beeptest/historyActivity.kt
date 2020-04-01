package com.example.beeptest

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_history.*

class historyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val context=this
        var db=myDBHandler(context)
        var scores =db.readAllUsers()
        val listview=findViewById<ListView>(R.id.scoresListShow)
        listview.adapter= MyCustomAdapter(this,scores)



    }
    private class MyCustomAdapter(context:Context,scores:ArrayList<scoresDatabase> ): BaseAdapter(){
        private val mContext:Context
        private val mscores=scores
        init{
            mContext=context
        }
        override fun getItem(position: Int): Any {
            return "TEST"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
           return mscores.size
        }
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val layoutinflater= LayoutInflater.from(mContext)
            val rowMain=layoutinflater.inflate(R.layout.row_main,viewGroup,false)
           var datetextview= rowMain.findViewById<TextView>(R.id.textViewDate)
            datetextview.text=mscores.get(position).scoredate
            var distancetextview=rowMain.findViewById<TextView>(R.id.textViewDistance)
            distancetextview.text=mscores.get(position).distance.toString()
            var leveltextview=rowMain.findViewById<TextView>(R.id.textViewLevel)
            leveltextview.text=mscores.get(position).level.toString()
            var shuttletextview=rowMain.findViewById<TextView>(R.id.textViewShuttle)
            shuttletextview.text=mscores.get(position).shuttle.toString()
            return rowMain

        }
    }
    }
