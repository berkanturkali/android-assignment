package com.arabam.android.assigment.ui.fragments.listing

import com.arabam.android.assigment.R
import com.arabam.android.assigment.base.BaseFragment
import com.arabam.android.assigment.databinding.FragmentHomeLayoutBinding
import com.arabam.android.assigment.ui.viewmodel.HomeFragmentViewModel

class HomeFragment:BaseFragment<FragmentHomeLayoutBinding,HomeFragmentViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_home_layout

    override fun getVM(): HomeFragmentViewModel {
        TODO("Not yet implemented")
    }

    override fun bindVM(binding: FragmentHomeLayoutBinding, vm: HomeFragmentViewModel) {
        TODO("Not yet implemented")
    }

    override fun init() {
        TODO("Not yet implemented")
    }
}