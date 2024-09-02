package com.selfpro.realies.feature.main

import android.view.View
import androidx.activity.viewModels
import androidx.compose.runtime.traceEventStart
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavOptions
import com.selfpro.navigation.NavData
import com.selfpro.navigation.NavigationRoute
import com.selfpro.realies.R
import com.selfpro.realies.databinding.ActivityMainBinding
import com.selfpro.realies.util.base.BaseActivity
import io.ktor.http.CacheControl

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()
    override fun start() {
        extendEdgeToEdge(R.id.main)

        //일기 추가 애니메이션 정의
        val addDNavOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.anim_down_first)
            .setExitAnim(R.anim.anim_stay)
            .build()

        binding.bnMain.navigationStart = R.id.realiesFragment
        binding.bnMain.navigationRoute = NavigationRoute(
            this,
            R.id.nav_host_fragment,
            listOf(
                NavData(binding.niRealies, R.id.realiesFragment),
                NavData(binding.niSubscribes, R.id.subscribesFragment),
                NavData(binding.niAdd, R.id.addFragment, addDNavOptions, true ) {
                    handleBottomNavigationVisibility(false)
                },
                NavData(binding.niChallenge, R.id.challengeFragment),
                NavData(binding.niMy, R.id.myFragment)
            )
        )

//        binding.bnMain.visibility = View.VISIBLE
//        binding.bnMain.minimumHeight = 10
//        binding.bnMain.addView(binding.bnMain)

        val a = View.VISIBLE
    }

    override fun handleBottomNavigationVisibility(state: Boolean) {
        handleVisibility(binding.bnMain, state)
    }
}