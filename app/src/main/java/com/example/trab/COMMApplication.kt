package com.example.trab

import android.app.Application
import com.example.trab.db.COMMRepository
import com.example.trab.db.COMMRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class COMMApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { COMMRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { COMMRepository(database.ComDao()) }

}