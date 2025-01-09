package com.example.trab.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trab.entities.Comentarios
import kotlinx.coroutines.flow.Flow


@Dao
interface ComentariosDao {
    @Query ("SELECT * FROM comentarios_table")
    fun getComment(): Flow<List<Comentarios>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(comentarios: Comentarios)

    @Query("DELETE FROM comentarios_table")
    suspend fun deleteAll()

}