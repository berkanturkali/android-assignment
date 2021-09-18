package com.arabam.android.assigment.ui.fragments.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arabam.android.assigment.data.model.year.YearItem
import com.arabam.android.assigment.databinding.FragmentFilterByYearLayoutBinding
import com.arabam.android.assigment.utils.Constants.YEAR_KEY
import com.arabam.android.assigment.utils.setNavigationResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import timber.log.Timber

class FilterByYearFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFilterByYearLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFilterByYearLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.applyButton.setOnClickListener {
            val maxYear = if(binding.maxYearEt.text.toString().isEmpty()) null else binding.maxYearEt.text.toString().toInt()
            val minYear = if(binding.minYearEt.text.toString().isEmpty()) null else binding.minYearEt.text.toString().toInt()
            setNavigationResult(YEAR_KEY,YearItem(minYear, maxYear))
            dialog?.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}