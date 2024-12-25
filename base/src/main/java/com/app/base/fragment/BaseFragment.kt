package com.app.base.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    companion object {
        private val TAG = BaseFragment::class.java.simpleName
    }

    lateinit var mActivity: AppCompatActivity
    lateinit var mContext: Context
    lateinit var mBinding: B

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "${javaClass.name} onAttach")

        mContext = context
        mActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "${javaClass.name} onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "${javaClass.name} onCreateView")

        mBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        initStatusBar(mBinding.root)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "${javaClass.name} onViewCreated")

        initLiveData()
        initView()
        initViewClick()
        initData()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "${javaClass.name} onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "${javaClass.name} onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "${javaClass.name} onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "${javaClass.name} onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "${javaClass.name} onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "${javaClass.name} onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "${javaClass.name} onDetach")
    }

    abstract fun getLayout(): Int

    abstract fun initView()

    open fun initViewClick() {}

    open fun initData() {}

    open fun initStatusBar(view: View) {
        setWindowInsetsListener(view)
    }

    open fun initLiveData() {}

    open fun isAddFragment(): Boolean {
        return !(isAdded && null != context && null != activity && !requireActivity().isFinishing)
    }

    fun setWindowInsetsListener(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}