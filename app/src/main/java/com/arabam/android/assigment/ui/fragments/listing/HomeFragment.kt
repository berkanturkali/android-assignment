package com.arabam.android.assigment.ui.fragments.listing

import androidx.fragment.app.viewModels
import com.arabam.android.assigment.R
import com.arabam.android.assigment.base.BaseFragment
import com.arabam.android.assigment.databinding.FragmentHomeLayoutBinding
import com.arabam.android.assigment.ui.viewmodel.HomeFragmentViewModel

class HomeFragment:BaseFragment<FragmentHomeLayoutBinding,HomeFragmentViewModel>() {

    private val mViewModel by viewModels<HomeFragmentViewModel>()

    private lateinit var binding: FragmentHomeLayoutBinding
    override val layoutId: Int
        get() = R.layout.fragment_home_layout

    override fun getVM(): HomeFragmentViewModel =mViewModel

    override fun bindVM(binding: FragmentHomeLayoutBinding, vm: HomeFragmentViewModel) {
        this.binding = binding
    }

    override fun init() {

    }
}