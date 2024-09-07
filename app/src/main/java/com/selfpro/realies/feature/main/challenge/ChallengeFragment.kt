package com.selfpro.realies.feature.main.challenge

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.selfpro.realies.R
import com.selfpro.realies.SharedViewModel
import com.selfpro.realies.databinding.FragmentChallengeBinding
import com.selfpro.realies.util.base.BaseFragment

class ChallengeFragment :
    BaseFragment<FragmentChallengeBinding, ChallengeViewModel>(R.layout.fragment_challenge) {
    override val viewModel: ChallengeViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun start() {
        binding.composeNews.setContent { ChallengeScreen(sharedViewModel = sharedViewModel) }
    }
}