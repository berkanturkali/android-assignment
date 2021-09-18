package com.arabam.android.assigment.ui.fragments.details.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arabam.android.assigment.R
import com.arabam.android.assigment.data.model.DetailAdvert
import com.arabam.android.assigment.databinding.FragmentDescriptionLayoutBinding
import com.arabam.android.assigment.utils.Constants

class DescriptionFragment : Fragment(R.layout.fragment_description_layout) {

    private lateinit var binding: FragmentDescriptionLayoutBinding

    private lateinit var mAdvert: DetailAdvert

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDescriptionLayoutBinding.bind(view)
        mAdvert = arguments?.getParcelable<DetailAdvert>(Constants.ADVERT_KEY)!!
        binding.advert = mAdvert
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }

}