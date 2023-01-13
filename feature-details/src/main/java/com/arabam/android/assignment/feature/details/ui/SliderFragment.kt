package com.arabam.android.assignment.feature.details.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assignment.core.common.R.id.nav_host_container
import com.arabam.android.assignment.feature.details.R.layout
import com.arabam.android.assignment.feature.details.adapter.SliderFragmentAdapter
import com.arabam.android.assignment.feature.details.databinding.FragmentSliderBinding
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SliderFragment : Fragment(layout.fragment_slider) {

    private lateinit var binding: FragmentSliderBinding

    private val args by navArgs<SliderFragmentArgs>()

    @Inject
    lateinit var adapter: SliderFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = nav_host_container
            duration = 300
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSliderBinding.bind(view)
        binding.adapter = adapter
        adapter.submitList(args.images.toList())
        val rv = binding.imageSlider.getChildAt(0) as RecyclerView
        rv.apply {
            viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                    scrollToPosition(args.position)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}
