package com.example.trab.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.trab.dao.ComentariosDao
import com.example.trab.entities.Comentarios
import kotlinx.coroutines.flow.Flow


class COMMRepository (private val ComDao: ComentariosDao) {


    //val all: Flow<List<Comentarios>> = wordDao.getComment(empresaId)
    fun getComment(empresaName: String): LiveData<List<Comentarios>> {
        return ComDao.getComment(empresaName).asLiveData()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(comentarios: Comentarios) {
        ComDao.insert(comentarios)
    }

    suspend fun deleteAll() {
        ComDao.deleteAll()
    }

    suspend fun deleteThis(texto: String){
        ComDao.deleteThis(texto)
    }



}

