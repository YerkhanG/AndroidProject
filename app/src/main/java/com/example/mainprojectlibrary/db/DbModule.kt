package com.example.mainprojectlibrary.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DbModule {
    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context : Context) : BookDataBase{
        return Room
            .databaseBuilder(context, BookDataBase::class.java, "Book Database")
            .build()
    }
    @Provides
    fun getBookDao(db : BookDataBase): BookDao = db.BookDao()
}