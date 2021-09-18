package com.arabam.android.assigment.ui.fragments.listing.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.arabam.android.assigment.data.ItemClickListener
import com.arabam.android.assigment.data.model.sort.SortItem
import com.arabam.android.assigment.data.model.sort.getSortList
import com.arabam.android.assigment.databinding.FragmentSortLayoutBinding
import com.arabam.android.assigment.ui.adapters.SortAdapter
import com.arabam.android.assigment.utils.Constants.SORT_KEY
import com.arabam.android.assigment.utils.setNavigationResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SortFragment : BottomSheetDialogFragment(), ItemClickListener<SortItem> {

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
            setNavigationResult(SORT_KEY, SortItem(""))
            dialog?.dismiss()
        }
        initRecycler()
    }

    private fun initRecycler() {
        binding.adapter = mAdapter
        mAdapter.setClickListener(this)
        val orderList = getSortList().map {
            if (it.title == args.lastOrder.title &&
                it.direction?.value == args.lastOrder.direction?.value
            ) {
                it.isSelected = true
            }
            it
        }
        mAdapter.submitList(orderList)
        binding.clearBtn.isVisible = orderList.any { it.isSelected }
    }

    override fun onClick(item: SortItem) {
        setNavigationResult(SORT_KEY, item)
        dialog?.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}