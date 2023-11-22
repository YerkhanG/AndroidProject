package com.example.mainprojectlibrary.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.mainprojectlibrary.databinding.ItemBookBinding
import com.example.mainprojectlibrary.db.BookEntity

class BookListAdapter(
    private val items : List<BookEntity?>
) : RecyclerView.Adapter<BaseBookViewHolder<*,BookEntity?>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseBookViewHolder<*, BookEntity?> {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseBookViewHolder<*, BookEntity?>, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

}

class BookViewHolder(override val binding : ItemBookBinding) :
        BaseBookViewHolder<ItemBookBinding,BookEntity?>(binding){

    override fun bindView(item: BookEntity?) {
        binding.title.text = item?.title
        binding.author.text =  item?.author
    }

}

abstract class BaseBookViewHolder<VB : ViewBinding, T>(protected open val binding: VB) : RecyclerView.ViewHolder(binding.root){
    abstract fun bindView(item : T)
}