package com.arabam.android.assigment.ui.fragments.listing

import androidx.fragment.app.viewModels
import com.arabam.android.assigment.R
import com.arabam.android.assigment.base.BaseFragment
import com.arabam.android.assigment.databinding.FragmentHomeLayoutBinding
import com.arabam.android.assigment.ui.adapters.ListingAdapter
import com.arabam.android.assigment.ui.viewmodel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment:BaseFragment<FragmentHomeLayoutBinding,HomeFragmentViewModel>() {

    private val mViewModel by viewModels<HomeFragmentViewModel>()

    @Inject
    lateinit var mAdapter:ListingAdapter

    private lateinit var binding: FragmentHomeLayoutBinding
    override val layoutId: Int
        get() = R.layout.fragment_home_layout

    override fun getVM(): HomeFragmentViewModel =mViewModel

    override fun bindVM(binding: FragmentHomeLayoutBinding, vm: HomeFragmentViewModel) {
        binding.adapter = mAdapter
        this.binding = binding
    }

    override fun init() {
        subscribeObservers()
    }

    private fun subscribeObservers(){
        launchOnLifecycleScope {
            mViewModel.adverts.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }
}