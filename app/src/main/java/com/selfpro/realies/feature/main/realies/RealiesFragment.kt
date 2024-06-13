package com.selfpro.realies.feature.main.realies

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.selfpro.realies.R
import com.selfpro.realies.adapter.NewsThumbAdapter
import com.selfpro.realies.data.model.NewsThumbModel
import com.selfpro.realies.databinding.FragmentRealiesBinding
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.base.BaseFragment

class RealiesFragment :
    BaseFragment<FragmentRealiesBinding, RealiesViewModel>(R.layout.fragment_realies) {
    override val viewModel: RealiesViewModel by viewModels()

    override fun start() {
        val newsThumbAdapter = NewsThumbAdapter()
        val newsThumbLayoutManager = LinearLayoutManager(requireContext())

        binding.rvNewsThumb.apply {
            adapter = newsThumbAdapter
            layoutManager = newsThumbLayoutManager
        }

        val newsThumbModel = NewsThumbModel(
            title = "Realies 출시하자마자 파산 위기",
            thumbImage = "",
            createdAt = "",
            broadCasterImage = "",
            subThumb = listOf()
        )

        newsThumbAdapter.submitList(listOf(newsThumbModel, newsThumbModel, newsThumbModel))
    }
}