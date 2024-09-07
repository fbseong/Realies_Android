package com.selfpro.realies.feature.main.my

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.selfpro.realies.R
import com.selfpro.realies.SharedViewModel
import com.selfpro.realies.databinding.FragmentMyBinding
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.base.BaseFragment

class MyFragment: BaseFragment<FragmentMyBinding, MyViewModel>(R.layout.fragment_my) {
    override val viewModel: MyViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun start() {
        binding.composeMy.setContent {
            MyScreen(sharedViewModel)
        }
    }
}