package com.summer.module

import com.app.base.activity.BaseVMActivity
import com.summer.module.databinding.ActivityMainBinding
import com.summer.module.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseVMActivity<MainViewModel, ActivityMainBinding>() {

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        super.initView()
        mViewModel.fetchData()
    }

}



