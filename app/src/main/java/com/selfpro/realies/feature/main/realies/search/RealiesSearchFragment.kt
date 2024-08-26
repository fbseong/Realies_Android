package com.selfpro.realies.feature.main.realies.search

import androidx.fragment.app.viewModels
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.selfpro.realies.R
import com.selfpro.realies.adapter.SearchAdapter
import com.selfpro.realies.databinding.FragmentSearchBinding
import com.selfpro.realies.util.base.BaseFragment

class RealiesSearchFragment :
    BaseFragment<FragmentSearchBinding, RealiesSearchViewModel>(R.layout.fragment_search) {
    override val viewModel: RealiesSearchViewModel by viewModels()

    override fun start() {
        val searchRecentAdapter = SearchAdapter {
            binding.editSearch.apply {
                setText(it)
                setSelection(it.length)
            }
        }
        val searchReentLayoutManager = FlexboxLayoutManager(requireContext())

        binding.rvRecent.apply {
            adapter = searchRecentAdapter
            layoutManager = searchReentLayoutManager.apply {
                flexWrap = FlexWrap.WRAP
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }
        }

        val list = listOf(
            "wfwegewg",
            "ewfewfwe",
            "we",
            "ewfew",
            "efw",
            "efef",
            "fefe",
            "wfewfewfew",
            "ewfewf",
            "wefewfewfewfew",
            "fe",
            "ef",
            "ef",
            "fe",
        )

        searchRecentAdapter.submitList(list)
    }
}