package com.selfpro.navigation

import android.view.View
import androidx.annotation.IdRes
import androidx.navigation.NavOptions

data class NavData(
    val navItem: BottomNavigationItem,
    @IdRes val navDestination: Int,
    val navOptions: NavOptions = NavOptions.Builder().build(),
    val disposable: Boolean = false,
    val clickEvent: ()->(Unit) = {},
)