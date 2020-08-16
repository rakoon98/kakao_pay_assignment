package com.kun.kakaopayassignment.views.detail

import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.kun.kakaopayassignment.R
import com.kun.kakaopayassignment.databinding.FragmentBookDetailBinding
import com.kun.kakaopayassignment.views.base.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailBookFragment : BaseFragment<FragmentBookDetailBinding>(){

    override val layoutResourceId: Int get() = R.layout.fragment_book_detail
    private val detailBookArguments by navArgs<DetailBookFragmentArgs>()

    override fun doBind() {
        bindingView.apply {
            lifecycleOwner = this@DetailBookFragment
            detailBack.setOnClickListener { popBack(this.root) }

            detailBookArguments.bookItem?.let {
                item = it
            } ?: kotlin.run {
                Toast.makeText(context, "책 데이터를 제대로 받지 못했습니다", Toast.LENGTH_SHORT).show()
                popBack(this.root)
            }

            executePendingBindings()
        }
    }

    override fun onObserve() {}

    private fun popBack(v:View){
        Navigation.findNavController(v).popBackStack()
    }

}