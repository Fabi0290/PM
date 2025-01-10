package com.example.trab.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.trab.dao.ComentariosDao
import com.example.trab.entities.Comentarios
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = arrayOf(Comentarios::class), version = 5, exportSchema = false)
abstract class COMMRoomDatabase : RoomDatabase() {

    abstract fun ComDao(): ComentariosDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    // Add sample words.
                    var comment= Comentarios(1,"Boa app","Citin",4.0f)
                    database.ComDao().insert(comment)

                }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    database.ComDao().deleteAll()
                    var comment= Comentarios(1,"Boa empresa","Citin",3.0f)
                    database.ComDao().insert(comment)
                    var comment2= Comentarios(2,"bom estagio","EduTech",4.0f)
                    database.ComDao().insert(comment2)
                    //ADICIONAR NOVA TABELA
                    //var fruta= Fruta(1,"ananas","amarelo")
                    //database.wordDao().insertFruta(fruta)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: COMMRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): COMMRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    COMMRoomDatabase::class.java,
                    "Comm_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}