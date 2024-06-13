package com.selfpro.navigation

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainer
import androidx.navigation.findNavController

class NavigationRoute(
    val activity: AppCompatActivity,
    val fragmentContainer: Int,
    val navDestinations: List<NavData>
) {

}