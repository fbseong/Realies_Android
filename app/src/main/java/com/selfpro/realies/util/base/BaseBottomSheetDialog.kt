package com.selfpro.realies.util.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialog<VB: ViewDataBinding>(@LayoutRes private val layoutId: Int) : BottomSheetDialogFragment() {

    protected val TAG = this.javaClass.simpleName

    protected var _binding: VB? = null
    val binding: VB
        get() = _binding ?: throw IllegalStateException("binding fail")

    // 버튼이 한개인지 두개인지
    enum class ButtonType{
        ONE, TWO
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvent()
    }

    abstract fun initView()

    open fun initEvent() {}

}