package com.arabam.android.assigment

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.arabam.android.assigment.databinding.ActivityMainBinding
import com.arabam.android.assigment.ui.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController:NavController

    private val mViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initNavigation()
        subscribeObservers()
    }

    private fun initNavigation(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.home, R.id.favorites))
        binding.toolbar
            .setupWithNavController(navController, appBarConfiguration)
        binding.contentMain.bottomNavigationView.setupWithNavController(navController)
    }

    private fun subscribeObservers(){
        lifecycleScope.launchWhenCreated {
            mViewModel.showProgress.collectLatest {
                binding.contentMain.progressBar.isVisible = it.show
            }
        }
    }
}