package com.arabam.android.assignment.feature.details.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arabam.android.assignment.core.common.utils.Constants
import com.arabam.android.assignment.feature.details.R
import com.arabam.android.assignment.feature.details.databinding.FragmentDescriptionLayoutBinding

class DescriptionFragment : Fragment(R.layout.fragment_description_layout) {

    private lateinit var binding: FragmentDescriptionLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDescriptionLayoutBinding.bind(view)
        val description = arguments?.getString(Constants.DESCRIPTION_KEY)!!
        binding.description = description
        binding.nsvDetail.isNestedScrollingEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}
