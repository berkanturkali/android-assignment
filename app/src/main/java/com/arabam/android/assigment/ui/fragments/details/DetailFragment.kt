package com.arabam.android.assigment.ui.fragments.details

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.arabam.android.assigment.R
import com.arabam.android.assigment.base.BaseFragment
import com.arabam.android.assigment.data.ImageClickListener
import com.arabam.android.assigment.data.Resource
import com.arabam.android.assigment.data.model.DetailAdvert
import com.arabam.android.assigment.databinding.FragmentDetailLayoutBinding
import com.arabam.android.assigment.ui.adapters.AdvertImagesViewPagerAdapter
import com.arabam.android.assigment.ui.adapters.DetailsFragmentPagerAdapter
import com.arabam.android.assigment.ui.fragments.details.tabs.DescriptionFragment
import com.arabam.android.assigment.ui.fragments.details.tabs.InfoFragment
import com.arabam.android.assigment.ui.viewmodel.DetailFragmentViewModel
import com.arabam.android.assigment.utils.load
import com.arabam.android.assigment.utils.resize
import com.arabam.android.assigment.utils.showSnack
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailLayoutBinding, DetailFragmentViewModel>(),ImageClickListener {

    private val mViewModel by viewModels<DetailFragmentViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    @Inject
    lateinit var imagePagerAdapter: AdvertImagesViewPagerAdapter

    private var currentAnimator: Animator? = null

    private var shortAnimationDuration: Int = 0

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
        imagePagerAdapter.setListener(this)
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
        launchOnLifecycleScope {
            activityViewModel.isFavChecked.collectLatest {
                if(it){
                    graphViewModel.addToFav(args.advert)
                }else{
                    graphViewModel.removeFromFav(args.advert)
                }
            }
        }
        launchOnLifecycleScope {
            activityViewModel.setIsInFavorites(graphViewModel.getAdvert(args.advert.id) != null)
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



    private fun zoomImageFromThumb(thumbView: View, url: String) {

        currentAnimator?.cancel()

        val expandedImageView: ImageView = binding.expandedImage
        expandedImageView.load(url.resize("800x600"))

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

    override fun onImageClick(imageView: ImageView, url: String) {
        zoomImageFromThumb(imageView, url)
    }

}