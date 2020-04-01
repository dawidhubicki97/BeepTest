package com.example.beeptest

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.widget.Toast

val DATABASE_NAME="MyDB"
val TABLE_NAME="scoresDatabase"
val COL_ID="id"
val COL_SCOREDATE="scoredate"
val  COL_LEVEL="level"
val COL_SHUTTLE="shuttle"
val COL_DISTANCE="distance"

class myDBHandler(var context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase) {
        val createtable="CREATE TABLE "+TABLE_NAME+" ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_SCOREDATE+" VARCHAR(256), "+ COL_LEVEL+" INTEGER, "+ COL_SHUTTLE+" INTEGER, "+ COL_DISTANCE+ " INTEGER)"

        db.execSQL(createtable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
    fun insertData(scoresDatabase: scoresDatabase){
        val db=this.writableDatabase
        var cv=ContentValues()

        cv.put(COL_SCOREDATE,scoresDatabase.scoredate)
        cv.put(COL_LEVEL,scoresDatabase.level)
        cv.put(COL_SHUTTLE,scoresDatabase.shuttle)
        cv.put(COL_DISTANCE,scoresDatabase.distance)

        var result=db.insert(TABLE_NAME,null,cv)
        if(result==-1.toLong())
            Toast.makeText(context,"FAIL",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Saved",Toast.LENGTH_SHORT).show()


    }
    fun readAllUsers(): ArrayList<scoresDatabase> {
        val scores = ArrayList<scoresDatabase>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from scoresdatabase", null)
        } catch (e: SQLiteException) {

        }

        var id:Int
        var scoredate: String
        var level:Int
        var shuttle:Int
        var distance:Int
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                id = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COL_ID))
                scoredate = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COL_SCOREDATE))
                level = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COL_LEVEL))
                shuttle = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COL_SHUTTLE))
                distance = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COL_DISTANCE))

                scores.add(scoresDatabase(id, scoredate, level,shuttle,distance))
                cursor.moveToNext()
            }
        }
        return scores
    }

}

