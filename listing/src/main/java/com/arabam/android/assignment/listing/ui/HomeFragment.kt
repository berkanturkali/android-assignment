package com.arabam.android.assignment.listing.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.arabam.android.assignment.common.base.BaseFragment
import com.arabam.android.assignment.common.utils.Constants
import com.arabam.android.assignment.common.utils.Constants.CATEGORY_KEY
import com.arabam.android.assignment.common.utils.Constants.YEAR_KEY
import com.arabam.android.assignment.common.utils.getNavigationResult
import com.arabam.android.assignment.listing.R
import com.arabam.android.assignment.listing.adapter.AdvertLoadStateAdapter
import com.arabam.android.assignment.listing.adapter.ListingAdapter
import com.arabam.android.assignment.listing.databinding.FragmentHomeLayoutBinding
import com.arabam.android.assignment.listing.model.category.CategoryItem
import com.arabam.android.assignment.listing.model.sort.SortItem
import com.arabam.android.assignment.listing.model.year.YearItem
import com.arabam.android.assignment.listing.viewmodel.HomeFragmentViewModel
import com.arabam.android.assignment.remote.model.ItemClickListener
import com.arabam.android.assignment.remote.model.ListingAdvert
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeLayoutBinding>(),
    ItemClickListener<ListingAdvert> {

    private val mViewModel by viewModels<HomeFragmentViewModel>()

    @Inject
    lateinit var mAdapter: ListingAdapter

    private lateinit var binding: FragmentHomeLayoutBinding
    override val layoutId: Int
        get() = R.layout.fragment_home_layout

    private var isGridMode = false

    override fun bind(binding: FragmentHomeLayoutBinding) {
        this.binding = binding
        binding.adapter = mAdapter
        subscribeObservers()
        getNavigationResult<SortItem>(R.id.home, Constants.SORT_KEY) {
            mViewModel.updateSortOrder(it)
        }
        getNavigationResult<YearItem>(R.id.home, YEAR_KEY) {
            mViewModel.updateYear(it)
        }
        getNavigationResult<CategoryItem>(R.id.home, CATEGORY_KEY) {
            mViewModel.updateCategory(it)
        }
        initAdapter()
        binding.filterByDateBtn.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeToFilterByYearFragment(mViewModel.getYearItem())
            findNavController().navigate(action)
        }
        binding.sortBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToSortFragment(mViewModel.getSortItem())
            findNavController().navigate(action)
        }
        binding.modelBtn.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeToCategoryContainerFragment(
                    mViewModel.getCategory()
                        ?: -1
                )
            findNavController().navigate(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun initAdapter() {
        binding.layoutManager = GridLayoutManager(requireContext(), 1)
        mAdapter.apply {
            setListener(this@HomeFragment)
            binding.advertsRv.adapter = withLoadStateHeaderAndFooter(
                header = AdvertLoadStateAdapter { mAdapter.retry() },
                footer = AdvertLoadStateAdapter { mAdapter.retry() }
            )
            addLoadStateListener { loadState ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && itemCount == 0
                binding.advertsRv.isVisible = !isListEmpty
                showProgress(loadState.source.refresh is LoadState.Loading)
                binding.emptyList.isVisible = isListEmpty
                binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error
            }
        }
    }

    private fun subscribeObservers() {
        mViewModel.adverts.observe(viewLifecycleOwner) {
            launchOnLifecycleScope {
                mAdapter.submitData(it)
            }
        }
        launchOnLifecycleScope {
            mViewModel.viewPreferencesFlow.collectLatest {
                isGridMode = it.isRecyclerViewInGridMode
                mAdapter.setGridMode(isGridMode)
                binding.advertsRv.layoutManager?.requestSimpleAnimationsInNextLayout()
                mAdapter.notifyItemRangeChanged(0, mAdapter.itemCount)
            }
        }
        lifecycleScope.launch {
            mAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collectLatest { binding.advertsRv.scrollToPosition(0) }
        }
    }

    private fun showProgress(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.grid_list_button -> {
                mViewModel.setGridMode(!isGridMode)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(item: ListingAdvert) {
        val action = HomeFragmentDirections.actionHomeToDetailsGraph(item.id)
        findNavController().navigate(action)
    }
}
