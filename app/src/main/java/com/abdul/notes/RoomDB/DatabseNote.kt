package com.abdul.notes.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1)
abstract class DatabseNote : RoomDatabase() {

    abstract fun getDao(): DAONote

    companion object {

        private var INSTANCE: DatabseNote? = null

        fun getDatabase(context: Context): DatabseNote {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabseNote::class.java, "notes_table"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}