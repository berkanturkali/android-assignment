package com.arabam.android.assignment.details.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.ScaleAnimation
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.ToggleButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arabam.android.assignment.commons.base.BaseFragment
import com.arabam.android.assignment.commons.bindings.load
import com.arabam.android.assignment.commons.utils.showSnack
import com.arabam.android.assignment.detail.DetailAdvert
import com.arabam.android.assignment.details.ImageClickListener
import com.arabam.android.assignment.details.R
import com.arabam.android.assignment.details.adapter.AdvertImagesViewPagerAdapter
import com.arabam.android.assignment.details.adapter.DetailsFragmentPagerAdapter
import com.arabam.android.assignment.details.databinding.FragmentDetailLayoutBinding
import com.arabam.android.assignment.details.tabs.DescriptionFragment
import com.arabam.android.assignment.details.tabs.InfoFragment
import com.arabam.android.assignment.details.viewmodel.DetailFragmentViewModel
import com.arabam.android.assignment.listing.model.ListingAdvert
import com.arabam.android.assignment.listing.model.Resource
import com.arabam.android.assignment.utils.resize
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import javax.inject.Inject

private const val TAG = "DetailFragment"
@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailLayoutBinding, DetailFragmentViewModel>(),
    ImageClickListener, CompoundButton.OnCheckedChangeListener {

    private val mViewModel by viewModels<DetailFragmentViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    @Inject
    lateinit var imagePagerAdapter: AdvertImagesViewPagerAdapter

    @Inject
    lateinit var circularProgressDrawable: CircularProgressDrawable

    private var currentAnimator: Animator? = null

    private var shortAnimationDuration: Int = 0

    private lateinit var scaleAnimation: ScaleAnimation

    private lateinit var favButton: ToggleButton

    private lateinit var advert: ListingAdvert

    private lateinit var viewpagerAdapter: DetailsFragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private lateinit var tabLayoutMediator: TabLayoutMediator
    private val titles = arrayOf("İlan Bilgileri", "Açıklama")

    private val fragments = listOf(
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
        imagePagerAdapter.setListener(this)
        mViewModel.getAdvert(args.id)
        subscribeObservers()
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
                        Timber.d(advert.photos.get(0).resize())
                        bindAdvert(advert)
                        this.advert = ListingAdvert(
                            category = advert.category,
                            date = advert.date,
                            dateFormatted = advert.dateFormatted,
                            id = advert.id,
                            location = advert.location,
                            modelName = advert.modelName,
                            photo = advert.photos.get(0).resize(),
                            price = advert.price,
                            priceFormatted = advert.priceFormatted,
                            properties = advert.properties,
                            title = advert.title
                        )
                        mViewModel.isAdvertInFavorites(advert.id)
                    }
                    showProgress(false)
                    favButton.isVisible = true
                }
            }
        }
        launchOnLifecycleScope {
            mViewModel.isAdvertInDb.collectLatest {
                favButton.setOnCheckedChangeListener(null)
                favButton.isChecked = it
                favButton.setOnCheckedChangeListener(this)
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
            DetailsFragmentPagerAdapter(childFragmentManager, lifecycle, fragments, advert, this)
        binding.viewpager.adapter = viewpagerAdapter
        binding.viewpager.isUserInputEnabled = false
        tabLayoutMediator = TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = titles[position]
        }
        tabLayoutMediator.attach()
    }


    private fun zoomImageFromThumb(thumbView: View, url: String) {

        currentAnimator?.cancel()

        val expandedImageView: ImageView = binding.expandedImage
        expandedImageView.load(url.resize(), circularProgressDrawable)

        val startBoundsInt = Rect()
        val finalBoundsInt = Rect()
        val globalOffset = Point()

        thumbView.getGlobalVisibleRect(startBoundsInt)
        binding.root
            .getGlobalVisibleRect(finalBoundsInt, globalOffset)
        startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
        finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

        val startBounds = RectF(startBoundsInt)
        val finalBounds = RectF(finalBoundsInt)

        val startScale: Float
        if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
            startScale = startBounds.height() / finalBounds.height()
            val startWidth: Float = startScale * finalBounds.width()
            val deltaWidth: Float = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            startScale = startBounds.width() / finalBounds.width()
            val startHeight: Float = startScale * finalBounds.height()
            val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        thumbView.alpha = 0f
        expandedImageView.visibility = View.VISIBLE
        binding.viewpager.visibility = View.INVISIBLE
        binding.tabs.visibility = View.INVISIBLE

        expandedImageView.pivotX = 0f
        expandedImageView.pivotY = 0f
        currentAnimator = AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(
                expandedImageView,
                View.X,
                startBounds.left,
                finalBounds.left)
            ).apply {
                with(ObjectAnimator.ofFloat(expandedImageView,
                    View.Y,
                    startBounds.top,
                    finalBounds.top))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f))
            }
            duration = shortAnimationDuration.toLong()
            interpolator = DecelerateInterpolator()
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    currentAnimator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    currentAnimator = null
                }
            })
            start()
        }
        expandedImageView.setOnClickListener {
            currentAnimator?.cancel()
            currentAnimator = AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale))
                }
                duration = shortAnimationDuration.toLong()
                interpolator = DecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        binding.viewpager.visibility = View.VISIBLE
                        binding.tabs.visibility = View.VISIBLE
                        currentAnimator = null
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        binding.viewpager.visibility = View.VISIBLE
                        binding.tabs.visibility = View.VISIBLE
                        currentAnimator = null
                    }
                })
                start()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_details_menu, menu)
        scaleAnimation = ScaleAnimation(
            0.7f,
            1.0f,
            0.7f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.7f,
            Animation.RELATIVE_TO_SELF,
            0.7f
        )
        scaleAnimation.duration = 500
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation.interpolator = bounceInterpolator
        val item = menu.findItem(R.id.add_to_fav_button)
        item.setActionView(R.layout.fav_toggle)
        favButton = item.actionView.findViewById(R.id.add_to_fav)
        favButton.setOnCheckedChangeListener(this)
        favButton.isVisible = false
    }

    override fun onImageClick(imageView: ImageView, url: String) {
        zoomImageFromThumb(imageView, url)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        buttonView?.startAnimation(scaleAnimation)
        if (isChecked) {
            mViewModel.addToFav(advert)
        } else {
            mViewModel.removeFromFav(advert)
        }
    }
}