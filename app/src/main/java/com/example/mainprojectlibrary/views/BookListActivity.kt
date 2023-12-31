package com.example.mainprojectlibrary.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mainprojectlibrary.MainActivity
import com.example.mainprojectlibrary.R
import com.example.mainprojectlibrary.databinding.ActivityBooksBinding
import com.example.mainprojectlibrary.db.Book
import com.example.mainprojectlibrary.recycler.BookListAdapter
import com.example.mainprojectlibrary.viewModel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookListActivity : AppCompatActivity() {
    private val viewModel : BookViewModel by viewModels()
    private val binding : ActivityBooksBinding by lazy { ActivityBooksBinding.inflate(layoutInflater)}
    private val adapter = BookListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding?.bottomNavView?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.page_1 -> {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
                R.id.page_2 -> {
                    Toast.makeText(this, "Already selected", Toast.LENGTH_SHORT).show()
                    return@setOnItemSelectedListener true
                }

                else -> {false}
            }
        }
        viewModel.loadingLiveData.observe(this){
            binding.listview.adapter = adapter
        }

        binding.listview.layoutManager = LinearLayoutManager(this)

    }
}