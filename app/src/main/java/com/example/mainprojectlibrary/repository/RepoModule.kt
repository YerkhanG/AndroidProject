package com.example.mainprojectlibrary.repository

import com.example.mainprojectlibrary.db.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {
    @Singleton
    @Provides
    fun provideBookRepository(dao : BookDao) : BookRepository = BookRepositoryImpl(dao)
}