package edu.ucne.appboletos.data.local.entity

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Dao
@Entity(tableName = "EventoReference")
data class Evento(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val referencia: Int
)