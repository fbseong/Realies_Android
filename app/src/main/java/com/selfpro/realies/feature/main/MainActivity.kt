package com.selfpro.realies.feature.main

import androidx.activity.viewModels
import com.selfpro.navigation.NavData
import com.selfpro.navigation.NavigationRoute
import com.selfpro.realies.R
import com.selfpro.realies.databinding.ActivityMainBinding
import com.selfpro.realies.util.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()
    override fun start() {
        extendEdgeToEdge(R.id.main)

        binding.bnMain.navigationRoute = NavigationRoute(
            this, R.id.nav_host_fragment, listOf(
                NavData(binding.niRealies, R.id.realiesFragment)
            )
        )
        supportFragmentManager
    }
}