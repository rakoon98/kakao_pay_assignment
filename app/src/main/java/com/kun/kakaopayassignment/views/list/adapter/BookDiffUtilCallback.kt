package com.kun.kakaopayassignment.views.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.kun.kakaopayassignment.model.data.BookDocument

class BookDiffUtilCallback : DiffUtil.ItemCallback<BookDocument> () {
    override fun areItemsTheSame(oldItem: BookDocument, newItem: BookDocument): Boolean = oldItem.isbn == newItem.isbn
    override fun areContentsTheSame(oldItem: BookDocument, newItem: BookDocument): Boolean = oldItem.contents == newItem.contents
}