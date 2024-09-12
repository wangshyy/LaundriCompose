package com.laundri.wsy.http.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 *  author : wsy
 *  date   : 2024/9/4
 *  desc   :
 */
open class BaseViewModel : ViewModel() {
    val TAG = this.javaClass.simpleName
    inline fun <T> launch(crossinline block: suspend () -> T) {
        viewModelScope.launch {
            block.invoke()
        }
    }
}