package com.arabam.android.assignment.listing.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.arabam.android.assignment.commons.utils.Constants.SELECTED_MAX_YEAR
import com.arabam.android.assignment.commons.utils.Constants.SELECTED_MIN_YEAR
import com.arabam.android.assignment.commons.utils.Constants.YEAR_KEY
import com.arabam.android.assignment.listing.R
import com.arabam.android.assignment.listing.databinding.FragmentFilterByYearLayoutBinding
import com.arabam.android.assignment.listing.model.year.YearItem
import com.example.core.utils.setNavigationResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class FilterByYearFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFilterByYearLayoutBinding

    private val args by navArgs<FilterByYearFragmentArgs>()

    private val array = getMinMaxList().toTypedArray()
    private val sortedByDescendingArray = array.sortedArrayDescending()

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
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        val clearItem = binding.toolbar.menu.findItem(R.id.clear)
        initNumberPicker()
        if (savedInstanceState != null) {
            val minValue = savedInstanceState.getInt(SELECTED_MIN_YEAR)
            val maxValue = savedInstanceState.getInt(SELECTED_MAX_YEAR)
            val isVisible = savedInstanceState.getBoolean("is_clear_visible")
            binding.minYearPicker.value = minValue
            binding.maxYearPicker.value = maxValue
            clearItem.isVisible = isVisible
        } else {
            clearItem.isVisible = args.year.minYear != null || args.year.maxYear != null
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.close -> {
                    dialog?.dismiss()
                    true
                }
                R.id.clear -> {
                    clearSelectedYears()
                    true
                }
                else -> true
            }
        }

        binding.selectBtn.setOnClickListener {
            val min =
                if (array[binding.minYearPicker.value] == "Yok") null else array[binding.minYearPicker.value].toInt()

            val max =
                if (sortedByDescendingArray[binding.maxYearPicker.value] == "Yok") null else sortedByDescendingArray[binding.maxYearPicker.value].toInt()
            setNavigationResult(YEAR_KEY, YearItem(min, max))
            dialog?.dismiss()
        }
    }

    private fun getMinMaxList(): List<String> {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val years = mutableListOf<String>()
        years.add(0, "Yok")
        for (i in 1980..year) {
            years.add(i.toString())
        }
        return years
    }

    private fun initNumberPicker() {
        binding.minYearPicker.apply {
            minValue = 0
            maxValue = array.size - 1
            wrapSelectorWheel = false
            displayedValues = array
            value = args.year.minYear?.let { array.indexOf(it.toString()) } ?: 0
        }
        binding.maxYearPicker.apply {
            minValue = 0
            maxValue = sortedByDescendingArray.size - 1
            wrapSelectorWheel = false
            displayedValues = sortedByDescendingArray
            value = args.year.maxYear?.let { sortedByDescendingArray.indexOf(it.toString()) } ?: 0
        }
    }

    private fun clearSelectedYears() {
        binding.minYearPicker.value = 0
        binding.maxYearPicker.value = 0
        binding.toolbar.menu.findItem(R.id.clear).isVisible = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SELECTED_MIN_YEAR, binding.minYearPicker.value)
        outState.putInt(SELECTED_MAX_YEAR, binding.maxYearPicker.value)
        outState.putBoolean("is_clear_visible", binding.toolbar.menu.findItem(R.id.clear).isVisible)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}