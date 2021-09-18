package com.arabam.android.assigment.ui.fragments.favorites

import com.arabam.android.assigment.R
import com.arabam.android.assigment.base.BaseFragment
import com.arabam.android.assigment.databinding.FragmentFavoritesLayoutBinding
import com.arabam.android.assigment.ui.viewmodel.AppGraphViewModel

class FavoritesFragment : BaseFragment<FragmentFavoritesLayoutBinding, AppGraphViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_favorites_layout

    override fun getVM(): AppGraphViewModel {
        TODO("Not yet implemented")
    }

    override fun bindVM(binding: FragmentFavoritesLayoutBinding, vm: AppGraphViewModel) {

    }

    override fun init() {

    }
}