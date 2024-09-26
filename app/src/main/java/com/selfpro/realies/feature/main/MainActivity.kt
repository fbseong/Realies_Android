package com.selfpro.realies.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.selfpro.realies.SharedViewModel
import com.selfpro.realies.feature.main.add.AddScreen
import com.selfpro.realies.feature.main.bottom.BottomNavigationBar
import com.selfpro.realies.feature.main.challenge.ChallengeScreen
import com.selfpro.realies.feature.main.my.MyScreen
import com.selfpro.realies.feature.main.news.NewsScreen
import com.selfpro.realies.feature.main.realies.RealiesScreen
import com.selfpro.realies.feature.main.realies.search.SearchScreen
import com.selfpro.realies.feature.main.sign.SignInScreen
import com.selfpro.realies.feature.main.subscribes.SubscribesScreen
import com.selfpro.realies.ui.color.SpColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val topPadding = remember { mutableStateOf(true) }
            val navController = rememberNavController()

            Column(
                modifier = (
                        if (topPadding.value) Modifier.windowInsetsPadding(WindowInsets.systemBars) else Modifier)
                    .background(SpColor.White),
            ) {
                SignInScreen(
                    modifier = Modifier.weight(1f),
                    isSignIn = true,
                    viewModel = sharedViewModel
                ) {

                    MainNavHost(navController = navController)
                    //MainScreen(viewModel = sharedViewModel)
                }
                BottomNavigationBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SpColor.White)
                        .height(48.dp),
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun MainNavHost(navController: NavHostController, viewModel: SharedViewModel = hiltViewModel()) {


    NavHost(navController = navController, startDestination = "realies") {
        composable("realies") {
            RealiesScreen()
        }
        composable("subscribes") {
            SubscribesScreen()
        }
        composable("challenges") {
            ChallengeScreen()
        }
        composable("my") {
            MyScreen()
        }
        composable("realiesSearch") {
            SearchScreen()
        }
        composable("news") {
            NewsScreen(viewModel)
        }
        composable("sign-in") {
//                SignInScreen()
        }
    }
}


//class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
//    override val viewModel: MainViewModel by viewModels()
//    override fun start() {
//        extendEdgeToEdge(R.id.main)
//
//        val memberClass = "Journalist"
//        if (memberClass == "Guest"){
//            binding.niSubscribes.visibility = View.GONE
//            binding.niAdd.visibility = View.GONE
//        }
//
//        //일기 추가 애니메이션 정의
//        val addDNavOptions = NavOptions.Builder()
//            .setEnterAnim(R.anim.anim_down_first)
//            .setExitAnim(R.anim.anim_stay)
//            .build()
//
//        binding.bnMain.navigationStart = R.id.realiesFragment
//        binding.bnMain.navigationRoute = NavigationRoute(
//            this,
//            R.id.nav_host_fragment,
//            listOf(
//                NavData(binding.niRealies, R.id.realiesFragment),
//                NavData(binding.niSubscribes, R.id.subscribesFragment),
//                NavData(binding.niAdd, R.id.addFragment, addDNavOptions, true) {
//                    handleBottomNavigationVisibility(false)
//                },
//                NavData(binding.niChallenge, R.id.challengeFragment),
//                NavData(binding.niMy, R.id.myFragment)
//            )
//        )
//    }
//    override fun handleBottomNavigationVisibility(state: Boolean) {
//        handleVisibility(binding.bnMain, state)
//    }
//}