package com.kun.kakaopayassignment.utils

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kun.kakaopayassignment.R
import com.kun.kakaopayassignment.viewmodels.ListViewModel

class ListUtils {
    companion object {
        @JvmStatic
        @BindingAdapter("setImage")
        fun setImage(iv: ImageView,url:String?) = Glide.with(iv).load(url).error(R.drawable.ic_no_image).into(iv)

        @JvmStatic
        @BindingAdapter("listViewModel")
        fun searchList(v:EditText, viewModel : ListViewModel?) {
            v.setOnEditorActionListener { v, actionId, event ->
                when(actionId){
                    EditorInfo.IME_ACTION_SEARCH -> {
                        viewModel?.searchBook(isSearch = true)
                    }
                    else -> {}
                }
                true
            }
        }

        @JvmStatic
        @BindingAdapter("moveWebBook")
        fun moveWebBook(v: View, url:String) =
            v.apply {
                setOnClickListener {
                    it.context.startActivity( Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
            }

    }
}