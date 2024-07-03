package com.selfpro.realies.feature.main.realies

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.selfpro.realies.R
import com.selfpro.realies.adapter.NewsThumbAdapter
import com.selfpro.realies.data.model.NewsThumbModel
import com.selfpro.realies.data.network.ktor.ClientKtor
import com.selfpro.realies.databinding.FragmentRealiesBinding
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.base.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RealiesFragment :
    BaseFragment<FragmentRealiesBinding, RealiesViewModel>(R.layout.fragment_realies) {
    override val viewModel: RealiesViewModel by viewModels()

    override fun start() {
        val newsThumbAdapter =
            NewsThumbAdapter(viewLifecycleOwner) { _titleSummaryFLow, title, content ->
                lifecycleScope.launch {
                    viewModel.getSummarizedTitle(_titleSummaryFLow, title, content).await()
                }
            }

        val newsThumbLayoutManager = LinearLayoutManager(requireContext())

        binding.rvNewsThumb.apply {
            adapter = newsThumbAdapter
            layoutManager = newsThumbLayoutManager
        }


        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getRecommendationRealies().await()

            viewModel.realeiesFlow.collect {
                newsThumbAdapter.submitList(
                    it.map {
                        NewsThumbModel(
                            title = it.title,
                            content = it.content,
                            publishedAt = it.publishedAt,
                            images = it.images ?: emptyList(),
                            provider = it.provider,
                            url = it.url,
                            author = it.author,
                            challengeRank = it.challengeRank,
                            subThumb = listOf()
                        )
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ClientKtor.close()
    }
}