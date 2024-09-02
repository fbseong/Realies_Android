package com.selfpro.realies.feature.main.subscribes

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.selfpro.realies.R
import com.selfpro.realies.SharedViewModel
import com.selfpro.realies.databinding.FragmentSubscribesBinding
import com.selfpro.realies.util.base.BaseFragment

class SubscribesFragment :
    BaseFragment<FragmentSubscribesBinding, SubscribesViewModel>(R.layout.fragment_subscribes) {
    override val viewModel: SubscribesViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun start() {
        binding.composeChallenges.setContent {
            SubscribesScreen(sharedViewModel)
        }
    }
}