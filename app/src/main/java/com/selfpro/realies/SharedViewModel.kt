package com.selfpro.realies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.selfpro.realies.data.model.LoadState
import com.selfpro.realies.data.model.MemberClass
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    lateinit var mainNavController: NavHostController

    var url: String? = null

    var memberClass = MemberClass.Guest

    var isTopPadding = false
}