package com.selfpro.realies.util.base

import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    protected val TAG = this.javaClass.simpleName
}
