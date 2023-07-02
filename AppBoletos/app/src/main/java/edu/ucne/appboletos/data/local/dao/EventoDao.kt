package edu.ucne.appboletos.data.local.dao

import kotlinx.coroutines.flow.Flow
import androidx.room.*
import edu.ucne.appboletos.data.local.entity.Evento

@Dao
interface EventoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvento(evento: Evento)

    @Update
    suspend fun updateEvento(evento: Evento)

    @Delete
    suspend fun deleteEvento(evento: Evento)

    @Query("SELECT * FROM EventoReference WHERE id = :id")
    fun getEvento(id: Int): Flow<Evento>

    @Query("SELECT * FROM EventoReference")
    fun getAll(): Flow<List<Evento>>
}