package com.arabam.android.assigment

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.ToggleButton
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

    private lateinit var navController: NavController

    private val mViewModel by viewModels<MainActivityViewModel>()

    private lateinit var favButton: ToggleButton

    private lateinit var scaleAnimation: ScaleAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.contentMain.viewmodel = mViewModel
        binding.viewmodel = mViewModel
        initNavigation()
        initFavButton()
        subscribeObservers()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.home, R.id.favorites))
        binding.toolbar
            .setupWithNavController(navController, appBarConfiguration)
        binding.contentMain.bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbarTitle.text = destination.label
            mViewModel.setFloatingVisibility(destination.id == R.id.home)
            binding.contentMain.bottomNavigationView.isVisible =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
            binding.addToFav.isVisible = destination.id == R.id.detailFragment
            binding.ivGrid.isVisible = destination.id ==R.id.home
        }
    }

    private fun initFavButton() {
        scaleAnimation = ScaleAnimation(
            0.7f,
            1.0f,
            0.7f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.7f,
            Animation.RELATIVE_TO_SELF,
            0.7f
        )
        scaleAnimation.duration = 500
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation.interpolator = bounceInterpolator
        favButton = binding.addToFav
    }

    private fun subscribeObservers() {
        lifecycleScope.launchWhenCreated {
            mViewModel.showProgress.collectLatest {
                binding.contentMain.progressBar.isVisible = it.show
            }
        }
        lifecycleScope.launchWhenCreated {
            mViewModel.isFloatingVisible.collectLatest {
                binding.contentMain.menu.isVisible = it
            }
        }
        lifecycleScope.launchWhenCreated {
            mViewModel.isAdvertInFavorites.collectLatest {
                favButton.setOnCheckedChangeListener(null)
                favButton.isChecked = it
                favButton.setOnCheckedChangeListener { element, isChecked ->
                    mViewModel.setFavChecked(isChecked)
                    element.startAnimation(scaleAnimation)
                }
            }
        }
    }
}