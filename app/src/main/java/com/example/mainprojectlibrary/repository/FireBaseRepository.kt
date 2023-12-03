package com.example.mainprojectlibrary.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mainprojectlibrary.db.Book
import com.example.mainprojectlibrary.db.BookDataBase
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject


interface FireBaseRepository {
    suspend fun getData()
    suspend fun removeData()
    suspend fun saveData(value: Book)


}


class FireBaseRepositoryImpl@Inject constructor(
    private val db : BookDataBase
) : FireBaseRepository {
    override suspend fun getData() {
       db.getData()
    }

    override suspend fun removeData() {
        db.removeData()
    }

    override suspend fun saveData(value: Book) {
        db.saveData(value)
    }

}