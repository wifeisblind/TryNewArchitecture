package com.gene.trynewarchitecture.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gene.trynewarchitecture.room.entity.ExceptionList
import com.gene.trynewarchitecture.room.entity.Task

@Database(
        entities = [
            ExceptionList::class,
            Task::class],
        version = 1,
        exportSchema = false
)
abstract class TMSDatabase : RoomDatabase() {

    abstract fun exceptionsDao(): ExceptionListDao

    abstract fun tasksDao(): TasksDao
}