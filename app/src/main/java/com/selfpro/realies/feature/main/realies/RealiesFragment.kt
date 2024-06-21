package com.selfpro.realies.feature.main.realies

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.selfpro.realies.R
import com.selfpro.realies.adapter.NewsThumbAdapter
import com.selfpro.realies.data.model.NewsThumbModel
import com.selfpro.realies.databinding.FragmentRealiesBinding
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.UiState
import com.selfpro.realies.util.base.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch

class RealiesFragment :
    BaseFragment<FragmentRealiesBinding, RealiesViewModel>(R.layout.fragment_realies) {
    override val viewModel: RealiesViewModel by viewModels()

    override fun start() {
        val newsThumbAdapter = NewsThumbAdapter() {
            ""

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
}