package edu.ucne.appboletos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.appboletos.data.local.dao.EventoDao
import edu.ucne.appboletos.data.local.entity.Evento

@Database(
    entities = [Evento::class],
    version = 1
)

abstract class AppDataBase : RoomDatabase() {
    abstract val eventosDao: EventoDao
}