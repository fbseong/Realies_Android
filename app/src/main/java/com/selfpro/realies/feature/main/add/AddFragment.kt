package com.selfpro.realies.feature.main.add

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.selfpro.realies.R
import com.selfpro.realies.databinding.FragmentAddBinding
import com.selfpro.realies.feature.main.MainActivity
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.base.BaseFragment

class AddFragment: BaseFragment<FragmentAddBinding, AddViewModel>(R.layout.fragment_add) {
    override val viewModel: AddViewModel by viewModels()

    override fun start() {
        binding.composeAdd.setContent {
            AddScreen()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (activity as MainActivity).handleBottomNavigationVisibility(true)
                findNavController().popBackStack()
            }
        })


    }
}