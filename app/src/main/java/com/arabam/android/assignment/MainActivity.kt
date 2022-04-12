package com.arabam.android.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.arabam.android.assignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        val inflater = navController.navInflater
        val graph = inflater.inflate(R.navigation.app_graph)
        navHostFragment.navController.graph = graph
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.home, R.id.favoritesFragment))
        binding.toolbar
            .setupWithNavController(navController, appBarConfiguration)
        binding.contentMain.bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbarTitle.text = destination.label
            binding.contentMain.bottomNavigationView.isVisible =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
        }
    }
}
