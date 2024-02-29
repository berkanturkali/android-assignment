package com.arabam.android.assignment.feature.listing.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.arabam.android.assignment.core.common.utils.Constants.CATEGORY_KEY
import com.arabam.android.assignment.core.common.utils.Constants.SELECTED_CATEGORY
import com.arabam.android.assignment.core.common.utils.Constants.SHOW_BADGE_ON_SELECT_BRAND_FRAGMENT
import com.arabam.android.assignment.core.common.utils.observe
import com.arabam.android.assignment.core.common.utils.setNavigationResult
import com.arabam.android.assignment.feature.listing.R
import com.arabam.android.assignment.feature.listing.databinding.FragmentCategoryContainerBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CategoryContainerFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCategoryContainerBinding
    private lateinit var navController: NavController

    private val viewModel by viewModels<CategoryContainerFragmentViewModel>()

    private val args by navArgs<CategoryContainerFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCategoryContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            childFragmentManager.findFragmentById(
                R.id.category_container_fragment
            ) as NavHostFragment
        navController = navHostFragment.navController
        val bundle = Bundle()
        bundle.putInt(SELECTED_CATEGORY, args.id)
        bundle.putBoolean(SHOW_BADGE_ON_SELECT_BRAND_FRAGMENT, args.showBadge)
        navController.setGraph(R.navigation.category_graph, bundle)
        binding.categoryToolbar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.categoryToolbarTitle.text = when (destination.id) {
                R.id.selectBrandFragment -> getString(R.string.select_brand)
                else -> ""
            }
        }
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.selectedCategoryItem.observe(viewLifecycleOwner) {
            if (it.id != -1) {
                setNavigationResult(CATEGORY_KEY, it)
                dialog?.dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}
