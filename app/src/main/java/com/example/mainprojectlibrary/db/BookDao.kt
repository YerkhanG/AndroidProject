package com.example.mainprojectlibrary.db

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

interface BookDao {
    val db: FirebaseDatabase
        get() = FirebaseDatabase.getInstance()
    fun getTableName(): String = "String"
    fun getClassType(): Class<Book> = Book::class.java
    private fun getDataLiveData1() = MutableLiveData<Book?>()
    val getDataLiveData: LiveData<Book?>
        get() = getDataLiveData1()

    private fun updateLiveData1() = MutableLiveData<Book?>()
    val updateLiveData: LiveData<Book?>
        get() = updateLiveData1()

    fun reference(){
        db.getReference(getTableName()).addValueEventListener(updateListener())
    }
    private fun updateListener() = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            updateLiveData1().postValue(snapshot.getValue(getClassType()))
        }

        override fun onCancelled(error: DatabaseError) {
            error.let {
                Log.e("FRDBWrapper", it.message)
            }
        }
    }
    fun saveData(value: Book, successSave: ((Boolean) -> Unit)? = null) {
        db.getReference(getTableName()).setValue(value) { error, _ ->
            successSave?.invoke(error == null)
            error?.let {
                Log.e("FRDBWrapper", it.message)
            }
        }
    }
    fun getData() {
        db.getReference(getTableName()).get().addOnSuccessListener {
            getDataLiveData1().postValue(it.getValue(getClassType()))
        }
    }
    fun removeData() {
        db.getReference(getTableName()).removeValue()
    }

}