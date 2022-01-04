package com.arabam.android.assignment.listing.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.arabam.android.assignment.commons.base.BaseFragment
import com.arabam.android.assignment.commons.utils.Constants
import com.arabam.android.assignment.commons.utils.Constants.CATEGORY_KEY
import com.arabam.android.assignment.commons.utils.Constants.YEAR_KEY
import com.arabam.android.assignment.listing.R
import com.arabam.android.assignment.listing.adapter.AdvertLoadStateAdapter
import com.arabam.android.assignment.listing.adapter.ListingAdapter
import com.arabam.android.assignment.listing.databinding.FragmentHomeLayoutBinding
import com.arabam.android.assignment.listing.model.ItemClickListener
import com.arabam.android.assignment.listing.model.ListingAdvert
import com.arabam.android.assignment.listing.model.category.CategoryItem
import com.arabam.android.assignment.listing.model.sort.SortItem
import com.arabam.android.assignment.listing.model.year.YearItem
import com.arabam.android.assignment.listing.viewmodel.HomeFragmentViewModel
import com.example.core.utils.getNavigationResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeLayoutBinding, HomeFragmentViewModel>(),
    ItemClickListener<ListingAdvert> {

    private val mViewModel by viewModels<HomeFragmentViewModel>()

    @Inject
    lateinit var mAdapter: ListingAdapter

    private lateinit var binding: FragmentHomeLayoutBinding
    override val layoutId: Int
        get() = R.layout.fragment_home_layout

    private var isGridMode = false

    override fun getVM(): HomeFragmentViewModel = mViewModel

    override fun bindVM(binding: FragmentHomeLayoutBinding, vm: HomeFragmentViewModel) {
        this.binding = binding
        binding.adapter = mAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun init() {
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
                HomeFragmentDirections.actionHomeToCategoryContainerFragment(mViewModel.getCategory()
                    ?: -1)
            findNavController().navigate(action)
        }
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

                val error = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error

                error?.let {
                    Toast.makeText(requireContext(),
                        it.error.localizedMessage,
                        Toast.LENGTH_SHORT)
                        .show()
                }
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
        launchOnLifecycleScope {
            mViewModel.shouldScrollToTop.collectLatest {
                if (it) {
                    binding.advertsRv.post {
                        binding.advertsRv.layoutManager?.scrollToPosition(0)
                    }
                }
            }
        }
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