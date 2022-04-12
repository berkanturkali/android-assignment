package com.arabam.android.assignment.details.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arabam.android.assignment.common.utils.Constants
import com.arabam.android.assignment.details.R
import com.arabam.android.assignment.details.adapter.InfoFragmentAdapter
import com.arabam.android.assignment.details.databinding.FragmentInfoLayoutBinding
import com.arabam.android.assignment.details.model.info.Info
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InfoFragment : Fragment(R.layout.fragment_info_layout) {

    private lateinit var binding: FragmentInfoLayoutBinding

    @Inject
    lateinit var mAdapter: InfoFragmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoLayoutBinding.bind(view)
        val infoList = arguments?.getParcelableArray(Constants.INFO_KEY)!!.toList() as List<Info>
        binding.adapter = mAdapter
        val dividerItemDecoration = DividerItemDecoration(
            binding.infoRv.context,
            LinearLayoutManager(requireContext()).orientation
        )
        binding.infoRv.addItemDecoration(dividerItemDecoration)
        mAdapter.submitList(infoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}
