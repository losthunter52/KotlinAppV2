package com.example.myApp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CreatureDao {

    @Query("SELECT * FROM localcreature")
    fun getAll(): LiveData<List<LocalCreature>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(creatures: List<LocalCreature>)

}