package com.example.mainprojectlibrary.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.mainprojectlibrary.databinding.ItemBookBinding
import com.example.mainprojectlibrary.db.Book

class BookListAdapter : ListAdapter<Book, BaseBookViewHolder<*, Book?>>(BookDiffUtils()) {

    class BookDiffUtils : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: BaseBookViewHolder<*, Book?>, position: Int) {
        holder.bindView(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseBookViewHolder<*, Book?> {
        return BookViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent ,false))
    }

}

class BookViewHolder(override val binding : ItemBookBinding) :
        BaseBookViewHolder<ItemBookBinding,Book?>(binding){

    override fun bindView(item: Book?) {
        binding.title.text = item?.title
        binding.author.text =  item?.author
    }

}

abstract class BaseBookViewHolder<VB : ViewBinding, T>(protected open val binding: VB) : RecyclerView.ViewHolder(binding.root){
    abstract fun bindView(item : T)
}