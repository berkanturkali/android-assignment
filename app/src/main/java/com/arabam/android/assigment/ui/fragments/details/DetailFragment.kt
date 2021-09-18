package com.arabam.android.assigment.ui.fragments.details

import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.arabam.android.assigment.R
import com.arabam.android.assigment.base.BaseFragment
import com.arabam.android.assigment.data.Resource
import com.arabam.android.assigment.data.model.DetailAdvert
import com.arabam.android.assigment.databinding.FragmentDetailLayoutBinding
import com.arabam.android.assigment.ui.adapters.AdvertImagesViewPagerAdapter
import com.arabam.android.assigment.ui.adapters.DetailsFragmentPagerAdapter
import com.arabam.android.assigment.ui.fragments.details.tabs.DescriptionFragment
import com.arabam.android.assigment.ui.fragments.details.tabs.InfoFragment
import com.arabam.android.assigment.ui.viewmodel.DetailFragmentViewModel
import com.arabam.android.assigment.utils.showSnack
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailLayoutBinding, DetailFragmentViewModel>() {

    private val mViewModel by viewModels<DetailFragmentViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    @Inject
    lateinit var imagePagerAdapter: AdvertImagesViewPagerAdapter

    private lateinit var viewpagerAdapter: DetailsFragmentPagerAdapter

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
        binding.adapter = imagePagerAdapter
        this.binding = binding
    }

    override fun init() {
        mViewModel.getAdvert(args.advert.id)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        mViewModel.advert.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading ->
                    activityViewModel.showProgress(true)
                is Resource.Error -> {
                    showSnack(it.message!!)
                    activityViewModel.showProgress(false)
                }
                is Resource.Success -> {
                    bindAdvert(it.data!!)
                    activityViewModel.showProgress(false)
                }
            }
        }
    }

    private fun bindAdvert(item: DetailAdvert) {
        initTabLayout(fragments, item)
        binding.noImageTv.isVisible = item.photos.isEmpty()
        imagePagerAdapter.setList(item.photos)
    }

    private fun initTabLayout(fragments: List<Fragment>, advert: DetailAdvert) {
        viewpagerAdapter =
            DetailsFragmentPagerAdapter(childFragmentManager, lifecycle, fragments, advert)
        binding.viewpager.adapter = viewpagerAdapter
        binding.viewpager.isUserInputEnabled = false
        tabLayoutMediator = TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = titles[position]
        }
        tabLayoutMediator.attach()
    }

}