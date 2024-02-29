package com.arabam.android.assignment.feature.listing.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arabam.android.assignment.feature.listing.adapter.CategoriesAdapter
import com.arabam.android.assignment.feature.listing.components.BrandList
import com.arabam.android.assignment.feature.listing.databinding.FragmentSelectBrandBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SelectBrandFragment :
    Fragment() {

    private lateinit var binding: FragmentSelectBrandBinding

    @Inject
    lateinit var adapter: CategoriesAdapter


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
        binding.brandList.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val categories by viewModel.categories.collectAsState()
                BrandList(
                    list = categories, onCategoryClick = { key, item ->
                        if (viewModel.categoryId != -1) {
                            item.firstOrNull {
                                it.id == viewModel.categoryId
                            }
                                .apply {
                                    this?.checked = true
                                }
                        }
                        val action =
                            SelectBrandFragmentDirections.actionSelectCategoryFragmentToSelectModelFragment(
                                item.toTypedArray(),
                                logo = key.logo!!
                            )
                        findNavController().navigate(action)
                    },
                    showBadge = viewModel.showBadge,
                    categoryId = viewModel.categoryId ?: -1

                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}
