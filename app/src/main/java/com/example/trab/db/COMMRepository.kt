package com.example.trab.db

import androidx.annotation.WorkerThread
import com.example.trab.dao.ComentariosDao
import com.example.trab.entities.Comentarios
import kotlinx.coroutines.flow.Flow


class COMMRepository (private val wordDao: ComentariosDao) {


    val allWords: Flow<List<Comentarios>> = wordDao.getComment()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(comentarios: Comentarios) {
        wordDao.insert(comentarios)
    }

    suspend fun deleteAll() {
        wordDao.deleteAll()
    }



}

