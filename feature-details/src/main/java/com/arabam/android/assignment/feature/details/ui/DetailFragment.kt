package com.arabam.android.assignment.feature.details.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arabam.android.assignment.core.common.R.drawable
import com.arabam.android.assignment.core.common.R.string
import com.arabam.android.assignment.core.common.base.BaseFragment
import com.arabam.android.assignment.core.common.utils.resize
import com.arabam.android.assignment.core.common.viewmodel.MainActivityViewModel
import com.arabam.android.assignment.core.model.DetailAdvert
import com.arabam.android.assignment.core.model.ListingAdvert
import com.arabam.android.assignment.core.model.Resource
import com.arabam.android.assignment.feature.details.R
import com.arabam.android.assignment.feature.details.R.id.profile
import com.arabam.android.assignment.feature.details.R.menu.fragment_details_menu
import com.arabam.android.assignment.feature.details.components.DetailsErrorView
import com.arabam.android.assignment.feature.details.components.DetailsLoadingView
import com.arabam.android.assignment.feature.details.databinding.FragmentDetailLayoutBinding
import com.arabam.android.assignment.feature.details.model.Option
import com.arabam.android.assignment.feature.details.model.OptionType
import com.arabam.android.assignment.feature.details.model.command.AddRemoveFavoritesCommand
import com.arabam.android.assignment.feature.details.model.command.OpenMessageScreenCommand
import com.arabam.android.assignment.feature.details.model.command.RequestCallCommand
import com.arabam.android.assignment.feature.details.model.info.getInfoList
import com.arabam.android.assignment.feature.details.viewmodel.DetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailLayoutBinding>() {
    private val mViewModel by viewModels<DetailFragmentViewModel>()

    private val activityViewModel by activityViewModels<MainActivityViewModel>()

    private val permissionResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                makeCall()
            } else {
                openDial()
            }
        }

    private lateinit var advert: DetailAdvert

    private lateinit var binding: FragmentDetailLayoutBinding
    override val layoutId: Int
        get() = R.layout.fragment_detail_layout

    override fun bind(binding: FragmentDetailLayoutBinding) {
        this.binding = binding
        binding.apply {
            details.setContent {

                val advertResource by mViewModel.advert.observeAsState()

                val lastVisitedAdverts by mViewModel.lastVisitedItems.observeAsState()

                val isFav by mViewModel.setFav.collectAsState()

                Box(modifier = Modifier.fillMaxSize()) {
                    when (advertResource) {
                        is Resource.Loading -> {
                            DetailsLoadingView()
                        }

                        is Resource.Error -> {
                            val error = (advertResource as Resource.Error).message
                            DetailsErrorView(
                                message = error ?: stringResource(id = string.something_went_wrong),
                                onTryAgainButtonClick = {
                                    mViewModel.id?.let(mViewModel::fetchAdvert)
                                })
                        }

                        is Resource.Success -> {
                            (advertResource as Resource.Success<DetailAdvert>).data?.let { advert ->
                                this@DetailFragment.advert = advert
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
                                mViewModel.insertAdvertInToLastVisitedItems(listingAdvert)
                                setMenuVisibility(true)
                                initOptionList()
                                DetailScreenContent(
                                    description = advert.text,
                                    images = advert.photos,
                                    infoList = getInfoList(advert, requireContext()),
                                    options = mViewModel.optionList,
                                    lastVisitedAdverts = lastVisitedAdverts?.filter { it.id != mViewModel.id } ?: emptyList(),
                                    isFav = isFav,
                                    onAdvertImageClick = { position ->
                                        val action =
                                            DetailFragmentDirections.actionDetailFragmentToSliderFragment(
                                                advert.photos.map { it.resize() }.toTypedArray(),
                                                position
                                            )
                                        findNavController().navigate(action)
                                    }
                                )
                            }
                        }

                        null -> Unit
                    }
                }
            }
        }
        setMenuVisibility(false)
        subscribeObservers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun subscribeObservers() {
        mViewModel.showBadgeOnFavoritesItem.observe(viewLifecycleOwner) {
            activityViewModel.setShowBadgeOnFavoritesItem(it)
        }
    }

    private fun initOptionList() {
        mViewModel.optionList =
            mutableListOf(
                Option(
                    drawable.ic_phone,
                    RequestCallCommand(permissionResultLauncher),
                    type = OptionType.CALL
                ),
                Option(
                    drawable.ic_star_outlined,
                    AddRemoveFavoritesCommand(mViewModel),
                    type = OptionType.FAVORITE
                ),
                Option(
                    drawable.ic_message,
                    OpenMessageScreenCommand(
                        requireContext(),
                        advert.userInfo.phoneFormatted
                    ),
                    type = OptionType.MESSAGE
                )
            )
    }

    private fun makeCall() {
        val number = "tel:" + advert.userInfo.phoneFormatted
        startActivity(Intent(Intent.ACTION_CALL, Uri.parse(number)))
    }

    private fun openDial() {
        val number = "tel:" + advert.userInfo.phoneFormatted
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse(number)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(fragment_details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            profile -> {
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
}
