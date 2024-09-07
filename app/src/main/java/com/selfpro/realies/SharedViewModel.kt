package com.selfpro.realies

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.selfpro.realies.data.model.MemberClass
import com.selfpro.realies.util.base.BaseViewModel


class SharedViewModel : BaseViewModel() {
    lateinit var mainNavController: NavHostController

    var url: String? = null

    var memberClass = MemberClass.Guest
}