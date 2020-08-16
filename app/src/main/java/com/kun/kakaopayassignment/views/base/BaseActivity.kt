package com.kun.kakaopayassignment.views.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() , LifecycleOwner {

    lateinit var bindingView: T
    abstract val layoutResourceId: Int

    abstract fun doBind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingView = DataBindingUtil.setContentView(this, layoutResourceId)

        doBind()
    }

}