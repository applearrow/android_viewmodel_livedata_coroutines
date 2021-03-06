package me.shkschneider.viewmodellivedatacoroutines.data

import androidx.annotation.WorkerThread
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.shkschneider.viewmodellivedatacoroutines.MainApplication

@Database(entities = [
    User::class,
    Project::class,
    Notification::class
], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun users(): Users
    abstract fun projects(): Projects
    abstract fun notifications(): Notifications

    companion object {

        private var INSTANCE: MyDatabase? = null

        @WorkerThread
        fun get(): MyDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .inMemoryDatabaseBuilder(MainApplication.applicationContext(), MyDatabase::class.java)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

    }

}