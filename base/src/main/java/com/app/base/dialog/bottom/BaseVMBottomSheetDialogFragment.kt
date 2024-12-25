package com.app.base.dialog.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.app.base.viewmodel.BaseViewModel

abstract class BaseVMBottomSheetDialogFragment<V : BaseViewModel, B : ViewDataBinding> : BaseBottomSheetDialogFragment<B>() {

    lateinit var mViewModel: V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val createView = super.onCreateView(inflater, container, savedInstanceState)
        mViewModel = createViewModel(getViewModelClass())
        return createView
    }

    protected open fun createViewModel(clazz: Class<V>): V {
        return ViewModelProvider(this)[clazz]
    }

    /**
     * 获取ViewModel的class
     */
    abstract fun getViewModelClass(): Class<V>
}