package com.arabam.android.assignment.feature.listing.ui.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.arabam.android.assignment.core.common.utils.Constants.SORT_KEY
import com.arabam.android.assignment.core.common.utils.setNavigationResult
import com.arabam.android.assignment.core.model.ItemClickListener
import com.arabam.android.assignment.feature.listing.adapter.SortAdapter
import com.arabam.android.assignment.feature.listing.databinding.FragmentSortLayoutBinding
import com.arabam.android.assignment.feature.listing.model.sort.SortItem
import com.arabam.android.assignment.feature.listing.model.sort.getSortList
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SortFragment : BottomSheetDialogFragment(),
    ItemClickListener<SortItem> {

    @Inject
    lateinit var mAdapter: SortAdapter

    private lateinit var binding: FragmentSortLayoutBinding

    private val args by navArgs<SortFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSortLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clearBtn.setOnClickListener {
            setNavigationResult(SORT_KEY, SortItem())
            dialog?.dismiss()
        }
        initRecycler()
    }

    private fun initRecycler() {
        binding.adapter = mAdapter
        mAdapter.setClickListener(this)
        val items = getSortList()
        if (args.lastOrder.type != null && args.lastOrder.direction != null) {
            items.first {
                it.type == args.lastOrder.type && it.direction == args.lastOrder.direction
            }
                .apply {
                    this.isSelected = true
                }
        }
        mAdapter.submitList(items)
        binding.clearBtn.isVisible = items.any { it.isSelected }
    }

    override fun onClick(item: SortItem) {
        setNavigationResult(SORT_KEY, item)
        dialog?.dismiss()
    }
}
