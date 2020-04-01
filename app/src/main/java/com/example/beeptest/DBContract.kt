package com.example.beeptest

import android.provider.BaseColumns

object DBContract {

    /* Inner class that defines the table contents */
    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "scoresDatabase"
            val COL_ID="id"
            val COL_SCOREDATE="scoredate"
            val  COL_LEVEL="level"
            val COL_SHUTTLE="shuttle"
            val COL_DISTANCE="distance"
        }
    }
}