package com.example.mainprojectlibrary.repository

import android.graphics.Bitmap
import com.example.mainprojectlibrary.db.BookDao
import com.example.mainprojectlibrary.db.BookEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface BookRepository {
    suspend fun save(book : String, author : String)
    suspend fun deleteAll()
    suspend fun deleteById(id : Int)
    suspend fun getByTitle(title : String) : List<BookEntity>
    suspend fun getAll() : List<BookEntity>

    var bookFlow: Flow<BookEntity>
}
class BookRepositoryImpl @Inject constructor(
    private val dao : BookDao
) : BookRepository{
    override suspend fun save(book: String,author: String) {
        dao.save(
            BookEntity(
            0,
            book,
            author
        )
        )
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }

    override suspend fun getByTitle(title: String): List<BookEntity> {
        return dao.getByTitle(title)
    }

    override suspend fun getAll(): List<BookEntity> {
        return dao.getAll()
    }


    override var bookFlow: Flow<BookEntity> = dao.getAllFlow()

}