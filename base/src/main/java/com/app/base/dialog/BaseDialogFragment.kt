package com.app.base.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.app.base.R
import com.blankj.utilcode.util.ScreenUtils

abstract class BaseDialogFragment<B : ViewDataBinding> : AppCompatDialogFragment() {
    companion object {
        private val TAG = BaseDialogFragment::class.java.simpleName
    }

    lateinit var mContext: Context
    lateinit var mActivity: AppCompatActivity
    lateinit var mBinding: B

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")

        mContext = context
        mActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setStyle(STYLE_NORMAL, R.style.dialog_dim)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG, "onCreateDialog")

        val dialog = AvoidLeakDialog(requireContext(), theme)
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
        initData()
        initClick()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")

        dialog?.window?.let { window ->
            window.attributes = window.attributes.apply {
                width = (ScreenUtils.getScreenWidth() * 0.95f).toInt()
                height = WindowManager.LayoutParams.WRAP_CONTENT
                gravity = Gravity.CENTER
                windowAnimations = android.R.style.Animation_Dialog
            }
        }
    }

    abstract fun getLayout(): Int
    abstract fun initView(view: View)
    open fun initData() {}
    open fun initClick() {}

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