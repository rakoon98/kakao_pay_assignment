package com.kun.kakaopayassignment.views.list.adapter

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.kun.kakaopayassignment.R
import com.kun.kakaopayassignment.databinding.HolderBookBinding
import com.kun.kakaopayassignment.model.data.BookDocument
import com.kun.kakaopayassignment.views.list.ListFragmentDirections

class HolderBook(
    private val viewBinding : HolderBookBinding
) :RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(item : BookDocument){
        viewBinding.apply {
            this.item = item
            bookItemLayout.setOnClickListener {
                ListFragmentDirections.actionListToDetail(item).run {
                    Navigation.findNavController(it).navigate(this)
                }
            }
            executePendingBindings()
        }
    }
}