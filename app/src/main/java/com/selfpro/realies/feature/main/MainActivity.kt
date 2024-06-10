package com.selfpro.realies.feature.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.selfpro.realies.R
import com.selfpro.realies.databinding.ActivityMainBinding
import com.selfpro.realies.util.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()
    override fun start() {
        extendEdgeToEdge(R.id.main)
    }
}