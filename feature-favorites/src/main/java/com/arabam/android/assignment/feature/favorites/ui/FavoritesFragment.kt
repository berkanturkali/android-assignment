package com.arabam.android.assignment.feature.favorites.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assignment.core.common.base.BaseFragment
import com.arabam.android.assignment.core.model.ItemClickListener
import com.arabam.android.assignment.core.model.ListingAdvert
import com.arabam.android.assignment.feature.favorites.FavoritesFragmentViewModel
import com.arabam.android.assignment.feature.favorites.R
import com.arabam.android.assignment.feature.favorites.adapter.FavoritesAdapter
import com.arabam.android.assignment.feature.favorites.databinding.FragmentFavoritesLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment :
    BaseFragment<FragmentFavoritesLayoutBinding>(),
    ItemClickListener<ListingAdvert> {

    @Inject
    lateinit var mAdapter: FavoritesAdapter

    private lateinit var binding: FragmentFavoritesLayoutBinding

    private val viewModel by viewModels<FavoritesFragmentViewModel>()

    override val layoutId: Int
        get() = R.layout.fragment_favorites_layout

    override fun bind(binding: FragmentFavoritesLayoutBinding) {
        this.binding = binding
        binding.adapter = mAdapter
        mAdapter.setListener(this)
        initSwipeToDelete()
        viewModel.favorites.observe(viewLifecycleOwner) {
            binding.favsRv.isVisible = it.isNotEmpty()
            binding.emptyView.isVisible = it.isEmpty()
            mAdapter.submitList(it)
        }
    }

    private fun initSwipeToDelete() {
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val advert = mAdapter.currentList[position]
                viewModel.removeFromFav(advert)
            }
        }

        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(binding.favsRv)
        }
    }

    override fun onClick(item: ListingAdvert) {
        val action = FavoritesFragmentDirections.actionFavoritesToDetailsGraph(item.id)
        findNavController().navigate(action)
    }
}
