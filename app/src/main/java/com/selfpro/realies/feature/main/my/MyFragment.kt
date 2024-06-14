package com.selfpro.realies.feature.main.my

import androidx.fragment.app.viewModels
import com.selfpro.realies.R
import com.selfpro.realies.databinding.FragmentMyBinding
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.base.BaseFragment

class MyFragment: BaseFragment<FragmentMyBinding, MyViewModel>(R.layout.fragment_my) {
    override val viewModel: MyViewModel by viewModels()
    override fun start() {
        SpLog.d(TAG)
    }
}