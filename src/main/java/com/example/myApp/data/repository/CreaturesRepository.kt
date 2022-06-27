package com.example.myApp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.myApp.data.domain.Creature
import com.example.myApp.data.local.CreatureDao
import com.example.myApp.data.local.asDomainModel
import com.example.myApp.data.source.OpenTibiaApi
import com.example.myApp.data.source.SourceCreatureContainer
import com.example.myApp.data.source.asLocalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreatureRepository(private val creatureDao: CreatureDao) {

    val creatures: LiveData<List<Creature>> = Transformations.map(
        creatureDao.getAll()
    ){
       it.asDomainModel()
    }

    suspend fun refreshCreature(){
        withContext(Dispatchers.IO){
            val creatures = OpenTibiaApi.retrofitService.getCreature()
            val creaturesContainer = SourceCreatureContainer(creatures)
            creatureDao.insertAll(creaturesContainer.asLocalModel())
        }
    }

}