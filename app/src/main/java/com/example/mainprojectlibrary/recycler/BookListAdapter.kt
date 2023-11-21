package com.example.mainprojectlibrary.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.mainprojectlibrary.databinding.ItemBookBinding
import com.example.mainprojectlibrary.db.BookEntity
import dagger.hilt.android.AndroidEntryPoint

class BookListAdapter(
    private val items : List<BookEntity>
) : RecyclerView.Adapter<BaseBookViewHolder<*,String , String>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseBookViewHolder<*, String , String> {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseBookViewHolder<*, String, String>, position: Int) {
        holder.bindView(items[position].title,items[position].author)
    }

    override fun getItemCount(): Int = items.size

}

class BookViewHolder(override val binding : ItemBookBinding) :
        BaseBookViewHolder<ItemBookBinding,String,String>(binding){
    override fun bindView(item1: String,item2 : String) {
        binding.title.text = item1
        binding.author.text =  item2
    }

}

abstract class BaseBookViewHolder<VB : ViewBinding, T , Y>(protected open val binding: VB) : RecyclerView.ViewHolder(binding.root){
    abstract fun bindView(item1 : T, item2 : Y)
}