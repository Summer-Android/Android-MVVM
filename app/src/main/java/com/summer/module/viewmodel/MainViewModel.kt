package com.summer.module.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.base.viewmodel.BaseViewModel
import com.app.network.state.ApiResponseState
import com.blankj.utilcode.util.LogUtils
import com.summer.module.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {

    fun fetchData() {
        viewModelScope.launch {
            val list = mainRepository.fetchData()
            list.collect {
                when (it) {
                    is ApiResponseState.SUCCESS -> {
                        LogUtils.e(list)
                    }

                    is ApiResponseState.ERROR -> {
                        LogUtils.e(it.error.message)
                    }
                }
            }
        }
    }

}