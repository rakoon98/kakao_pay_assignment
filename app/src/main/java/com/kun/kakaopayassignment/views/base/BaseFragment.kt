package com.kun.kakaopayassignment.views.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

abstract class BaseFragment<T : ViewDataBinding> : Fragment(), LifecycleOwner {

    lateinit var bindingView: T
    abstract val layoutResourceId: Int

    lateinit var fragment: Fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingView = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        return bindingView.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        doBind()
        onObserve()
    }

    abstract fun doBind()
    abstract fun onObserve()

}