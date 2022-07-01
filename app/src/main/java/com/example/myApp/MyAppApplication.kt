package com.example.myApp

import android.app.Application
import com.example.myApp.data.local.CreatureDatabase
import com.example.myApp.data.repository.CreatureRepository

class MyAppApplication: Application() {

    private val database: CreatureDatabase by lazy {
        CreatureDatabase.getInstance(this)
    }

    val repository: CreatureRepository by lazy {
        CreatureRepository(database.creatureDao())
    }

}