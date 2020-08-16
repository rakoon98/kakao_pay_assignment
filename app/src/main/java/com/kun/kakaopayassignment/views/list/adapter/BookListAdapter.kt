package com.kun.kakaopayassignment.views.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.kun.kakaopayassignment.databinding.HolderBookBinding
import com.kun.kakaopayassignment.model.data.BookDocument
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class BookListAdapter @Inject constructor():  RecyclerView.Adapter<HolderBook>() {
    private val bookDiffUtil = AsyncListDiffer(this, BookDiffUtilCallback())

    fun diffItem(bookList:List<BookDocument>?) = bookDiffUtil.submitList(bookList)
    fun getItem(position: Int) = bookDiffUtil.currentList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderBook
        = HolderBook(HolderBookBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    override fun getItemCount(): Int = bookDiffUtil.currentList.size
    override fun onBindViewHolder(holder: HolderBook, position: Int) = holder.bind(getItem(position))

}