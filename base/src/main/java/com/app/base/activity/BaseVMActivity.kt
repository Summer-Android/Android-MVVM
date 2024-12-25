package com.app.base.activity

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.app.base.viewmodel.BaseViewModel

abstract class BaseVMActivity<V : BaseViewModel, B : ViewDataBinding> : BaseActivity<B>() {

    lateinit var mViewModel: V

    override fun initView() {
        super.initView()
        mViewModel = createViewModel(getViewModelClass())
    }

    /**
     * 获取 ViewModel 的 Class
     */
    abstract fun getViewModelClass(): Class<V>

    private fun createViewModel(clazz: Class<V>): V {
        return ViewModelProvider(this)[clazz]
    }
}