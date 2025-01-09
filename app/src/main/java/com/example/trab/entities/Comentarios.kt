package com.example.trab.entities
import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow


@Entity(tableName = "Comentarios_table")
data class Comentarios (
    @PrimaryKey(autoGenerate = true) val id: Int?=null,
    @ColumnInfo(name="texto") val texto: String,
)