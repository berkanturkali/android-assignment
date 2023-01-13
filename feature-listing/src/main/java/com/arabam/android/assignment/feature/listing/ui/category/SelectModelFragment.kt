package com.arabam.android.assignment.feature.listing.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.arabam.android.assignment.feature.listing.databinding.FragmentSelectModelBinding
import com.arabam.android.assignment.feature.listing.model.category.CategoryItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SelectModelFragment : BottomSheetDialogFragment() {

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
        val isVisible = args.models.any(CategoryItem::checked)
        binding.clear.isVisible = isVisible
        binding.clear.setOnClickListener {
            containerViewModel.setSelectedCategoryId(CategoryItem())
            dialog?.dismiss()
        }
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            val items = mapModels(args.models)
            initModelPicker(items)
        }

        binding.cancelBtn.setOnClickListener {
            dialog?.dismiss()
        }
        binding.selectBtn.setOnClickListener {
            getSelectedIdThenApply()
        }
    }

    private fun getSelectedIdThenApply() {
        val item = args.models[binding.modelPicker.value]
        containerViewModel.setSelectedCategoryId(item)
        dialog?.dismiss()
    }

    private fun initModelPicker(items: Array<String?>) {
        binding.modelPicker.apply {
            minValue = 0
            maxValue = args.models.size - 1
            wrapSelectorWheel = false
            displayedValues = items
            value = args.models.indexOf(args.models.firstOrNull(CategoryItem::checked) ?: 0)
        }
    }

    private suspend fun mapModels(models: Array<CategoryItem>) = withContext(Dispatchers.Default) {
        models.map(CategoryItem::name)
            .toTypedArray()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}
