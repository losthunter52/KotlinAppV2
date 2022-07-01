package com.example.myApp.data.source

import com.example.myApp.data.domain.Creature
import com.example.myApp.data.local.LocalCreature


data class SourceCreatureContainer(
    val sourceCreature: List<SourceCreature>
)

data class SourceCreature (
    val name: String,
    val race: String,
    val image_url: String
)

fun SourceCreatureContainer.asDomainModel(): List<Creature> {
    return sourceCreature.map{
        Creature(
            name = it.name,
            race = it.race,
            image_url = it.image_url
        )
    }
}

fun SourceCreatureContainer.asLocalModel(): List<LocalCreature> {
    return sourceCreature.map{
        LocalCreature(
            name = it.name,
            race = it.race,
            image_url = it.image_url
        )
    }
}