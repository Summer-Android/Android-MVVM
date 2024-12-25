package com.app.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.app.base.viewmodel.BaseViewModel

abstract class BaseVMFragment<V : BaseViewModel, B : ViewDataBinding> : BaseFragment<B>() {

    lateinit var mViewModel: V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val createView = super.onCreateView(inflater, container, savedInstanceState)
        mViewModel = createViewModel(getViewModelClass())
        return createView
    }

    /**
     * 获取 ViewModel 的 Class
     */
    abstract fun getViewModelClass(): Class<V>

    protected open fun createViewModel(clazz: Class<V>): V {
        return ViewModelProvider(this)[clazz]
    }
}