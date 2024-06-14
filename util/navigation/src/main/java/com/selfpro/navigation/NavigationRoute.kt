package com.selfpro.navigation

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

class NavigationRoute(
    val appCompatActivity: AppCompatActivity,
    @IdRes val fragmentContainer: Int,
    val navDestinations: List<NavData>
)