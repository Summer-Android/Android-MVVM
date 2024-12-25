package com.app.base.dialog.bottom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.app.base.R
import com.app.base.dialog.AvoidLeakBottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<B : ViewDataBinding> : BottomSheetDialogFragment() {

    companion object {
        private val TAG = BaseBottomSheetDialogFragment::class.java.simpleName
    }

    lateinit var mContext: Context
    lateinit var mActivity: AppCompatActivity
    lateinit var mBinding: B

    val isFragmentDestroyed get() = !isAdded || isRemoving || isDetached

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")

        mContext = context
        mActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG, "onCreateDialog")

        val dialog = AvoidLeakBottomSheetDialog(requireContext(), R.style.bottom_sheet_dialog)
        dialog.setCanceledOnTouchOutside(true)
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView")

        mBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        initView(view)
        initLiveData()
        initData()
        initClick()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")

        setNavigationBarColor(Color.WHITE)
    }

    abstract fun getLayout(): Int
    abstract fun initView(view: View)
    open fun initLiveData() {}
    open fun initData() {}
    open fun initClick() {}

    fun setNavigationBarColor(@ColorInt color: Int) {
        dialog?.window?.navigationBarColor = color
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }
}