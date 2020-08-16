package com.kun.kakaopayassignment.views.list

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kun.kakaopayassignment.R
import com.kun.kakaopayassignment.databinding.FragmentListBinding
import com.kun.kakaopayassignment.viewmodels.ListViewModel
import com.kun.kakaopayassignment.views.base.BaseFragment
import com.kun.kakaopayassignment.views.list.adapter.BookListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_list

    private val listViewModel : ListViewModel by viewModels()

    @Inject
    lateinit var bookAdapter : BookListAdapter

    override fun doBind() {
        bindingView.apply {
            lifecycleOwner = this@ListFragment
            viewModel = listViewModel

            bookListRecyclerView.apply {
                adapter = bookAdapter
                listViewModel.addOnRecyclerViewInfinite(this)
            }

            executePendingBindings()
        }
    }

    override fun onObserve() {
        listViewModel.getErrorData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
        listViewModel.getBookData.observe(viewLifecycleOwner, Observer {
            bookAdapter.diffItem(it)
        })
    }

}