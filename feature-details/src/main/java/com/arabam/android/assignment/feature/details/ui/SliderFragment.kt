package com.arabam.android.assignment.feature.details.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.arabam.android.assignment.feature.details.R.layout
import com.arabam.android.assignment.feature.details.components.Slider
import com.arabam.android.assignment.feature.details.databinding.FragmentSliderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SliderFragment : Fragment(layout.fragment_slider) {

    private lateinit var binding: FragmentSliderBinding

    private val args by navArgs<SliderFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSliderBinding.bind(view)
        binding.slider.setContent {
            val images = args.images.toList()
            val initialPage = args.position
            Slider(initialPage = initialPage, images = images)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}
