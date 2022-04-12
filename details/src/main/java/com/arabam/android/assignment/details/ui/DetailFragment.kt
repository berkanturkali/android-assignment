package com.arabam.android.assignment.details.ui

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arabam.android.assignment.common.base.BaseFragment
import com.arabam.android.assignment.common.utils.showSnack
import com.arabam.android.assignment.details.ImageClickListener
import com.arabam.android.assignment.details.R
import com.arabam.android.assignment.details.adapter.AdvertImagesViewPagerAdapter
import com.arabam.android.assignment.details.adapter.DetailsFragmentPagerAdapter
import com.arabam.android.assignment.details.adapter.LastVisitedItemsAdapter
import com.arabam.android.assignment.details.databinding.FragmentDetailLayoutBinding
import com.arabam.android.assignment.details.tabs.DescriptionFragment
import com.arabam.android.assignment.details.tabs.InfoFragment
import com.arabam.android.assignment.details.viewmodel.DetailFragmentViewModel
import com.arabam.android.assignment.domain.utils.resize
import com.arabam.android.assignment.remote.model.DetailAdvert
import com.arabam.android.assignment.remote.model.ListingAdvert
import com.arabam.android.assignment.remote.model.Resource
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailLayoutBinding>(),
    ImageClickListener {
    private val mViewModel by viewModels<DetailFragmentViewModel>()

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                makeCall()
            } else {
                openDial()
            }
        }

    @Inject
    lateinit var imagePagerAdapter: AdvertImagesViewPagerAdapter

    @Inject
    lateinit var circularProgressDrawable: CircularProgressDrawable

    @Inject
    lateinit var lastVisitedAdapter: LastVisitedItemsAdapter

    private lateinit var viewpagerAdapter: DetailsFragmentPagerAdapter

    private lateinit var advert: DetailAdvert

    private lateinit var tabLayoutMediator: TabLayoutMediator
    private val titles = arrayOf("İlan Bilgileri", "Açıklama")

    private val fragments = listOf(
        InfoFragment(),
        DescriptionFragment()
    )

    private lateinit var binding: FragmentDetailLayoutBinding
    override val layoutId: Int
        get() = R.layout.fragment_detail_layout

    override fun bind(binding: FragmentDetailLayoutBinding) {
        this.binding = binding
        postponeEnterTransition()
        binding.apply {
            root.doOnPreDraw { startPostponedEnterTransition() }
            adapter = imagePagerAdapter
            lastVisitedRv.adapter = lastVisitedAdapter
        }
        setMenuVisibility(false)
        imagePagerAdapter.setListener(this)
        subscribeObservers()
        initButtons()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun makeCall() {
        val number = "tel:" + binding.callFab.contentDescription
        startActivity(Intent(Intent.ACTION_CALL, Uri.parse(number)))
    }

    private fun openDial() {
        val number = "tel:" + binding.callFab.contentDescription
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse(number)
        startActivity(intent)
    }

    private fun openMessageScreen() {
        val number = "sms:" + binding.messageFab.contentDescription
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(number)
        startActivity(intent)
    }

    private fun initButtons() {
        binding.addToFav.setOnClickListener {
            mViewModel.setFav.value?.let {
                if (it) {
                    mViewModel.removeFromFav(mViewModel.getAdvert())
                    mViewModel.setFav(false)
                } else {
                    mViewModel.addToFav(mViewModel.getAdvert())
                    mViewModel.setFav(true)
                }
            }
        }
        binding.callFab.setOnClickListener {
            requestPermission.launch(Manifest.permission.CALL_PHONE)
        }
        binding.messageFab.setOnClickListener {
            openMessageScreen()
        }
    }

    private fun subscribeObservers() {
        mViewModel.advert.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showProgress(true)
                }

                is Resource.Error -> {
                    showSnack(it.message!!)
                    showProgress(false)
                }
                is Resource.Success -> {
                    it.data?.let { advert ->
                        this.advert = advert
                        bindAdvert(advert)
                        val listingAdvert = ListingAdvert(
                            category = advert.category,
                            date = advert.date,
                            dateFormatted = advert.dateFormatted,
                            id = advert.id,
                            location = advert.location,
                            modelName = advert.modelName,
                            photo = advert.photos[0].resize(),
                            price = advert.price,
                            priceFormatted = advert.priceFormatted,
                            properties = advert.properties,
                            title = advert.title
                        )
                        mViewModel.initAdvert(listingAdvert)
                        setMenuVisibility(true)
                        binding.optionsLl.visibility = View.VISIBLE
                    }
                    showProgress(false)
                }
            }
        }
        mViewModel.isAdvertInDb.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { inDb ->
                val bg = if (inDb) R.drawable.ic_star else R.drawable.ic_star_outlined
                binding.addToFav.setImageResource(bg)
                mViewModel.setFav(inDb)
            }
        }

        launchOnLifecycleScope {
            mViewModel.setFav.collectLatest {
                it?.let {
                    val bg = if (it) R.drawable.ic_star else R.drawable.ic_star_outlined
                    binding.addToFav.setImageResource(bg)
                }
            }
        }
        mViewModel.lastVisitedItems.observe(viewLifecycleOwner) {
            lastVisitedAdapter.submitList(it)
        }

        mViewModel.showLastVisitedItems.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { show ->
                val visibility = if (show) View.VISIBLE else View.GONE
                binding.lastItemsLl.visibility = visibility
            }
        }
    }

    private fun showProgress(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else GONE
    }

    private fun bindAdvert(item: DetailAdvert) {
        initTabLayout(fragments, item)
        binding.callFab.contentDescription = item.userInfo.phoneFormatted
        binding.messageFab.contentDescription = item.userInfo.phoneFormatted
        binding.noImageTv.isVisible = item.photos.isEmpty()
        imagePagerAdapter.setList(item.photos)
    }

    private fun initTabLayout(fragments: List<Fragment>, advert: DetailAdvert) {
        viewpagerAdapter =
            DetailsFragmentPagerAdapter(childFragmentManager, lifecycle, fragments, advert, this)
        binding.viewpager.adapter = viewpagerAdapter
        binding.viewpager.isUserInputEnabled = false
        tabLayoutMediator = TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = titles[position]
        }
        tabLayoutMediator.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                showUserProfile()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showUserProfile() {
        val action =
            DetailFragmentDirections.actionDetailFragmentToUserInfoDialog(
                phone = advert.userInfo.phoneFormatted,
                name = advert.userInfo.nameSurname
            )
        findNavController().navigate(action)
    }

    override fun onImageClick(images: List<String>, position: Int, view: View) {
        val action =
            DetailFragmentDirections.actionDetailFragmentToSliderFragment(
                images.map { it.resize() }
                    .toTypedArray(),
                position
            )
        val transitionName = resources.getString(R.string.slider_transition_name)
        val extras = FragmentNavigatorExtras(view to transitionName)
        findNavController().navigate(action, extras)
        reenterTransition = MaterialElevationScale(true).apply {
            duration = 300
        }
    }
}
