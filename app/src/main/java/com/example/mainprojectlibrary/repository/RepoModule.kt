package com.example.mainprojectlibrary.repository

import com.example.mainprojectlibrary.db.BookDataBase
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {
    @Provides
    @Singleton
    fun provideFirebaseDatabaseInstance(): BookDataBase{
        return BookDataBase()
    }

    @Singleton
    @Provides
    fun provideBookRepository(db: BookDataBase) : FireBaseRepository = FireBaseRepositoryImpl(db)
}