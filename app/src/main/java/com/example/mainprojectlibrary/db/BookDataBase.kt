package com.example.mainprojectlibrary.db

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BookDataBase : RoomDatabase() {
    abstract fun BookDao(): BookDao
}