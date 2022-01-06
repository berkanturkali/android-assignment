package com.arabam.android.assignment.details.ui

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.arabam.android.assignment.details.R
import com.arabam.android.assignment.details.adapter.SliderFragmentAdapter
import com.arabam.android.assignment.details.databinding.FragmentSliderBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SliderFragment : Fragment(R.layout.fragment_slider) {

    private lateinit var binding: FragmentSliderBinding

    private val args by navArgs<SliderFragmentArgs>()

    @Inject
    lateinit var adapter: SliderFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSliderBinding.bind(view)
        binding.adapter = adapter
        adapter.submitList(args.images.toList())
        val rv = binding.imageSlider.getChildAt(0) as RecyclerView
        rv.apply {
            val itemCount = adapter?.itemCount ?: 0
            if (itemCount >= args.position) {
                viewTreeObserver.addOnGlobalLayoutListener(object :
                    ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
            }
            scrollToPosition(args.position)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}