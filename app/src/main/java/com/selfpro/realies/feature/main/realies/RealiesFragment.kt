package com.selfpro.realies.feature.main.realies

import androidx.compose.runtime.Composable
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.selfpro.realies.R
import com.selfpro.realies.SharedViewModel
import com.selfpro.realies.databinding.FragmentRealiesBinding
import com.selfpro.realies.feature.main.news.NewsScreen
import com.selfpro.realies.feature.main.realies.search.SearchScreen
import com.selfpro.realies.util.base.BaseFragment

class RealiesFragment :
    BaseFragment<FragmentRealiesBinding, RealiesViewModel>(R.layout.fragment_realies) {
    override val viewModel: RealiesViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun start() {


        binding.composeNews.setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()

        sharedViewModel.mainNavController = navController


        NavHost(navController = navController, startDestination = "realies") {
            composable("realies") {
                RealiesScreen(sharedViewModel)
            }
            composable("realiesSearch") {
                SearchScreen()
            }
            composable("news") {
                NewsScreen(sharedViewModel)
            }
            composable("sign-in"){
                SignInScreen()
            }
        }


    }
}

//        binding.editSearch.focusable = zfalse
//        binding.editSearch.setOnClickListener { findNavController().navigate(R.id.action_realiesFragment_to_searchFragment) }


//        val newsThumbAdapter =
//            NewsThumbAdapter(
//                requireContext(),
//                viewLifecycleOwner
//            ) { _titleSummaryFLow, title, content ->
//                lifecycleScope.launch {
//                    viewModel.getSummarizedTitle(_titleSummaryFLow, title, content).await()
//                }
//            }
//
//        val newsThumbLayoutManager = LinearLayoutManager(requireContext())
//
//        binding.rvNewsThumb.apply {
//            adapter = newsThumbAdapter
//            layoutManager = newsThumbLayoutManager
//        }
//
//
//        lifecycleScope.launch(Dispatchers.Main) {
//            viewModel.getRecommendationRealies().await()
//            viewModel.realeiesFlow.collect {
//                newsThumbAdapter.submitList(
//                    it.map {
//                        NewsThumbModel(
//                            title = it.title,
//                            content = it.content,
//                            publishedAt = it.publishedAt,
//                            images = it.images ?: emptyList(),
//                            provider = it.provider,
//                            url = it.url,
//                            author = it.author,
//                            challengeRank = it.challengeRank,
//                            subThumb = listOf()
//                        )
//                    }
//                )
//            }
//        }
//}
