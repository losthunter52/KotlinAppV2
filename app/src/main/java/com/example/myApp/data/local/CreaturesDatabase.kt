package com.example.myApp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalCreature::class], version = 1)
abstract class CreatureDatabase : RoomDatabase() {

    abstract fun creatureDao(): CreatureDao

    companion object {

        @Volatile
        private var INSTANCE: CreatureDatabase? = null

        fun getInstance(context: Context): CreatureDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CreatureDatabase::class.java,
                    "creature_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }

    }

}