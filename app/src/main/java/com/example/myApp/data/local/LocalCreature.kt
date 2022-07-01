package com.example.myApp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myApp.data.domain.Creature

@Entity
data class LocalCreature(
    @PrimaryKey
    val name: String,
    val race: String,
    val image_url: String
)

fun List<LocalCreature>.asDomainModel(): List<Creature> {
    return map{
        Creature(
            name = it.name,
            race = it.race,
            image_url = it.image_url
        )
    }
}
