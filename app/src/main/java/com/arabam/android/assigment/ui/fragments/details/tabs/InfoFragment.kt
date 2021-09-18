package com.arabam.android.assigment.ui.fragments.details.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arabam.android.assigment.R
import com.arabam.android.assigment.data.model.DetailAdvert
import com.arabam.android.assigment.data.model.info.getInfoList
import com.arabam.android.assigment.databinding.FragmentInfoLayoutBinding
import com.arabam.android.assigment.ui.adapters.InfoFragmentAdapter
import com.arabam.android.assigment.utils.Constants.ADVERT_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InfoFragment:Fragment(R.layout.fragment_info_layout) {

    private lateinit var advert: DetailAdvert

    private lateinit var binding:FragmentInfoLayoutBinding

    @Inject
    lateinit var mAdapter:InfoFragmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoLayoutBinding.bind(view)
        advert = arguments?.getParcelable<DetailAdvert>(ADVERT_KEY)!!
        binding.adapter = mAdapter
        val dividerItemDecoration = DividerItemDecoration(binding.infoRv.context,
            LinearLayoutManager(requireContext()).orientation)
        binding.infoRv.addItemDecoration(dividerItemDecoration)
        mAdapter.submitList(getInfoList(advert,requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}