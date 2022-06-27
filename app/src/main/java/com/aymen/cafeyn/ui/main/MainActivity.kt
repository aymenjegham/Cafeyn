package com.aymen.cafeyn.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.aymen.cafeyn.R
import com.aymen.cafeyn.databinding.ActivityMainBinding
import com.aymen.cafeyn.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel by viewModels<MainViewModel>()


    private val navFragment: Fragment
        get() = nav_host_fragment

    private val navHostFragment by lazy { navFragment as NavHostFragment }

    private val navGraph by lazy {
        navHostFragment
            .navController
            .navInflater
            .inflate(R.navigation.main_nav_graph)
    }

    private val navController by lazy { navHostFragment.navController }


    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        bind(binding)
    }

    private fun bind(binding: ActivityMainBinding) {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        registerMainObserver()
        setSupportActionBar(toolbar)
        initNavigation(binding)
    }

    private fun registerMainObserver() {
        registerBaseObservers(viewModel)
    }

    private fun initNavigation(binding: ActivityMainBinding) {
        appBarConfiguration = AppBarConfiguration(navGraph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

}