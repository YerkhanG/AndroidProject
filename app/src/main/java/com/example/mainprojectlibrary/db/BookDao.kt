package com.example.mainprojectlibrary.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface BookDao {
    @Query("Delete from books")
    fun deleteAll()
    @Query("Delete from books where id = :id")
    fun deleteById(id: Int)
    @Query("Select * from books")
    fun getAll(): List<BookEntity>

    @Query("Select * from books")
    fun getAllFlow(): Flow<BookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(bookEntity: BookEntity)

    @Query("Select * from books where title = :title")
    fun getByTitle(title: String) : List<BookEntity>
}