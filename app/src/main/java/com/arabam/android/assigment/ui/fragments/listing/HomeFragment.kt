package com.arabam.android.assigment.ui.fragments.listing

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.arabam.android.assigment.R
import com.arabam.android.assigment.base.BaseFragment
import com.arabam.android.assigment.data.model.sort.SortItem
import com.arabam.android.assigment.databinding.FragmentHomeLayoutBinding
import com.arabam.android.assigment.ui.adapters.AdvertLoadStateAdapter
import com.arabam.android.assigment.ui.adapters.ListingAdapter
import com.arabam.android.assigment.ui.viewmodel.HomeFragmentViewModel
import com.arabam.android.assigment.utils.Constants
import com.arabam.android.assigment.utils.getNavigationResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeLayoutBinding, HomeFragmentViewModel>() {

    private val mViewModel by viewModels<HomeFragmentViewModel>()

    @Inject
    lateinit var mAdapter: ListingAdapter

    private lateinit var binding: FragmentHomeLayoutBinding
    override val layoutId: Int
        get() = R.layout.fragment_home_layout

    override fun getVM(): HomeFragmentViewModel = mViewModel

    override fun bindVM(binding: FragmentHomeLayoutBinding, vm: HomeFragmentViewModel) {
        this.binding = binding
    }

    override fun init() {
        initAdapter()
        subscribeObservers()
        getNavigationResult<SortItem>(R.id.home, Constants.SORT_KEY) {

        }
    }

    private fun initAdapter() {
        mAdapter.apply {
            binding.advertsRv.adapter = withLoadStateHeaderAndFooter(
                header = AdvertLoadStateAdapter { mAdapter.retry() },
                footer = AdvertLoadStateAdapter { mAdapter.retry() }
            )
            addLoadStateListener { loadState ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && itemCount == 0
                activityViewModel.showProgress(loadState.source.refresh is LoadState.Loading)
                binding.advertsRv.isVisible = !isListEmpty
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
        launchOnLifecycleScope {
            mViewModel.adverts.collectLatest {
                mAdapter.submitData(it)
            }
        }
        launchOnLifecycleScope {
            activityViewModel.sortClick.collectLatest {
                if (it.isClicked) {

                }
            }
        }
        launchOnLifecycleScope {
            activityViewModel.yearFilterClick.collectLatest {
                if (it.isClicked) {

                }
            }
        }
    }
}