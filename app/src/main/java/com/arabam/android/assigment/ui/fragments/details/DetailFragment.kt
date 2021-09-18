package com.arabam.android.assigment.ui.fragments.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.arabam.android.assigment.R
import com.arabam.android.assigment.base.BaseFragment
import com.arabam.android.assigment.databinding.FragmentDetailLayoutBinding
import com.arabam.android.assigment.ui.fragments.details.tabs.DescriptionFragment
import com.arabam.android.assigment.ui.fragments.details.tabs.InfoFragment
import com.arabam.android.assigment.ui.viewmodel.DetailFragmentViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailFragment : BaseFragment<FragmentDetailLayoutBinding, DetailFragmentViewModel>() {

    private val mViewModel by viewModels<DetailFragmentViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    private lateinit var tabLayoutMediator: TabLayoutMediator
    private val titles = arrayOf("İlan Bilgileri", "Açıklama")

    private val fragments = listOf<Fragment>(
        InfoFragment(),
        DescriptionFragment()
    )

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