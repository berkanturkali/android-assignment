package com.arabam.android.assigment.ui.fragments.favorites

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assigment.R
import com.arabam.android.assigment.base.BaseFragment
import com.arabam.android.assigment.data.ItemClickListener
import com.arabam.android.assigment.data.model.ListingAdvert
import com.arabam.android.assigment.databinding.FragmentFavoritesLayoutBinding
import com.arabam.android.assigment.ui.adapters.FavoritesAdapter
import com.arabam.android.assigment.ui.viewmodel.AppGraphViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesLayoutBinding, AppGraphViewModel>(),ItemClickListener<ListingAdvert> {

    @Inject
    lateinit var mAdapter: FavoritesAdapter

    private lateinit var binding: FragmentFavoritesLayoutBinding

    override val layoutId: Int
        get() = R.layout.fragment_favorites_layout

    override fun getVM(): AppGraphViewModel =graphViewModel

    override fun bindVM(binding: FragmentFavoritesLayoutBinding, vm: AppGraphViewModel) {
        this.binding = binding
        binding.adapter = mAdapter
        mAdapter.setListener(this)
    }

    override fun init() {
        initSwipeToDelete()
        launchOnLifecycleScope {
            graphViewModel.favorites().collectLatest {
                binding.favsRv.isVisible = it.isNotEmpty()
                binding.emptyView.isVisible = it.isEmpty()
                mAdapter.submitList(it)
            }
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
                graphViewModel.removeFromFav(advert)
            }
        }

        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(binding.favsRv)
        }
    }

    override fun onClick(item: ListingAdvert) {
        val action = FavoritesFragmentDirections.actionFavoritesToDetailFragment(item)
        findNavController().navigate(action)
    }
}