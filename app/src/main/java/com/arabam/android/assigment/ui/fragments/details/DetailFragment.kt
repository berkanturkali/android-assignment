package com.arabam.android.assigment.ui.fragments.details

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.arabam.android.assigment.R
import com.arabam.android.assigment.base.BaseFragment
import com.arabam.android.assigment.databinding.FragmentDetailLayoutBinding
import com.arabam.android.assigment.ui.viewmodel.DetailFragmentViewModel

class DetailFragment : BaseFragment<FragmentDetailLayoutBinding, DetailFragmentViewModel>() {

    private val mViewModel by viewModels<DetailFragmentViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    private lateinit var binding: FragmentDetailLayoutBinding
    override val layoutId: Int
        get() = R.layout.fragment_detail_layout

    override fun getVM(): DetailFragmentViewModel = mViewModel

    override fun bindVM(binding: FragmentDetailLayoutBinding, vm: DetailFragmentViewModel) {
        this.binding = binding
    }

    override fun init() {

    }
}