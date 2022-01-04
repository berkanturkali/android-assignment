package com.arabam.android.assignment.listing.ui.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arabam.android.assignment.commons.utils.Constants.SELECTED_CATEGORY
import com.arabam.android.assignment.listing.R
import com.arabam.android.assignment.listing.adapter.CategoriesAdapter
import com.arabam.android.assignment.listing.databinding.FragmentSelectBrandBinding
import com.arabam.android.assignment.listing.model.ItemClickListener
import com.arabam.android.assignment.listing.model.category.CategoryItem
import com.example.core.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SelectBrandFragment : Fragment(),
    ItemClickListener<List<CategoryItem>> {

    private lateinit var binding: FragmentSelectBrandBinding

    @Inject
    lateinit var adapter: CategoriesAdapter

    private var categoryId: Int? = null

    private val viewModel by viewModels<SelectBrandFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSelectBrandBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryId = arguments?.getInt(SELECTED_CATEGORY)
        adapter.setListener(this)
        binding.brandsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SelectBrandFragment.adapter
            setHasFixedSize(true)
        }
        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.categories.observe(viewLifecycleOwner){
            it?.let {
                adapter.setCategories(it)
            }
        }
    }

    override fun onClick(item: List<CategoryItem>) {
        if (categoryId != -1) {
            item.firstOrNull {
                it.id == categoryId
            }.apply {
                this?.checked = true
            }
        }
        val action =
            SelectBrandFragmentDirections.actionSelectCategoryFragmentToSelectModelFragment(
                item.toTypedArray())
        findNavController().navigate(action)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}