package com.arabam.android.assignment.feature.listing.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.arabam.android.assignment.core.common.R.style
import com.arabam.android.assignment.feature.listing.components.ApplyButton
import com.arabam.android.assignment.feature.listing.components.ModelList
import com.arabam.android.assignment.feature.listing.components.SelectModelFragmentTopBar
import com.arabam.android.assignment.feature.listing.databinding.FragmentSelectModelBinding
import com.arabam.android.assignment.feature.listing.model.category.CategoryItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectModelFragment : DialogFragment() {

    override fun getTheme() = style.RoundedCornersDialog

    private lateinit var binding: FragmentSelectModelBinding

    private val args by navArgs<SelectModelFragmentArgs>()
    private val containerViewModel by viewModels<CategoryContainerFragmentViewModel>(
        ownerProducer = { requireParentFragment().requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSelectModelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.modelList.apply {
            setContent {

                val selectedItem = args.models.firstOrNull { it.checked }

                var selectedCategoryItem by rememberSaveable {
                    mutableStateOf(selectedItem ?: CategoryItem(-1))
                }

                Column {
                    SelectModelFragmentTopBar(logoRes = args.logo, onCloseButtonClick = {
                        dialog?.dismiss()
                    })
                    ModelList(
                        modelList = args.models.toList(),
                        onItemClick = { item ->
                            selectedCategoryItem = item
                        },
                        selectedItem = selectedCategoryItem
                    )
                    ApplyButton(onButtonClick = {
                        getSelectedIdThenApply(selectedCategoryItem.id)
                    }
                    )
                }
            }
        }
    }

    private fun getSelectedIdThenApply(id: Int?) {
        val item = args.models.firstOrNull { it.id == id }
        containerViewModel.setSelectedCategoryId(item ?: CategoryItem())
        dialog?.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}
