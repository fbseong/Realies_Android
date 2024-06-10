package com.selfpro.realies.util.base

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.selfpro.realies.R
import com.selfpro.realies.util.SpLog
import kotlin.properties.Delegates

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutRes: Int,

    ) : AppCompatActivity() {

    protected lateinit var binding: B
    protected lateinit var mViewModel: VM
    protected abstract val viewModel: VM

    var rootLayoutId: Int? = null

    protected val TAG = this.javaClass.simpleName

    lateinit var navData: List<NavData>

    protected open fun preLoad() {}
    protected abstract fun start()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preLoad()
        prepareDataBinding()
        start()

    }

    fun extendEdgeToEdge(layoutId: Int) {
        rootLayoutId = layoutId
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(rootLayoutId!!)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun prepareDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        mViewModel = if (::mViewModel.isInitialized) mViewModel else viewModel
//        binding.setVariable(BR.vm, viewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (::binding.isInitialized) binding.unbind()
    }

    fun handleStatusBarColor(color: Int) {
        if (rootLayoutId != null) {
            if (color == android.R.color.transparent) {
                ViewCompat.setOnApplyWindowInsetsListener(findViewById(rootLayoutId!!)) { v, insets ->
                    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    v.setPadding(
                        systemBars.left, 0, systemBars.right, systemBars.bottom
                    )
                    insets
                }
            } else {
                ViewCompat.setOnApplyWindowInsetsListener(findViewById(rootLayoutId!!)) { v, insets ->
                    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    v.setPadding(
                        systemBars.left, systemBars.top, systemBars.right, systemBars.bottom
                    )
                    insets
                }
            }
        } else SpLog.e(TAG, "rootLayoutId is not initialized")

        window.statusBarColor = ContextCompat.getColor(this, color)
    }

    fun setNavigation(fragmentContainer: Int, navList: List<NavData>) {
        navList[0].navButton.isActivated = true
        val navHost = supportFragmentManager.findFragmentById(fragmentContainer) as NavHostFragment

        navData = navList
        navData.map { nav ->
            nav.navButton.setOnClickListener {
                navData.map { nav ->
                    nav.navButton.isActivated = false
                }
                nav.navButton.isActivated = true
                navHost.findNavController().navigate(nav.destinationFragment)

            }
        }
    }

    fun handleNavigationBarColor(color: Int) {
        window.navigationBarColor = ContextCompat.getColor(this, color)
    }

    data class NavData(
        val navButton: View, val destinationFragment: Int
    )

    fun setNavId(navId: Int) {
        navData.map { navData ->
            navData.navButton.isActivated = navId == navData.destinationFragment
        }
    }

    open fun handleBottomNavigationVisibility(state: Boolean) {

    }

    private fun handleVisibility(view1: View, state: Boolean) {
        if (state) {
            view1.visibility = View.VISIBLE

        } else {
            view1.visibility = View.INVISIBLE
        }
    }

    fun handleVisibility(view1: View, view2: View, state: Boolean) {
        if (state) {
            view1.visibility = View.VISIBLE
            view2.visibility = View.VISIBLE
        } else {
            view1.visibility = View.INVISIBLE
            view2.visibility = View.INVISIBLE
        }
    }

    private fun handleVisibility(view1: View, view2: View, view3: View, state: Boolean) {
        if (state) {
            view1.visibility = View.VISIBLE
            view2.visibility = View.VISIBLE
            view3.visibility = View.VISIBLE
        } else {
            view1.visibility = View.INVISIBLE
            view2.visibility = View.INVISIBLE
            view3.visibility = View.INVISIBLE
        }
    }


}

