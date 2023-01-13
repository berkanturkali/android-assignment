package com.arabam.android.assignment.feature.details.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter.FragmentTransactionCallback.OnPostEventListener
import com.arabam.android.assignment.core.common.utils.Constants.DESCRIPTION_KEY
import com.arabam.android.assignment.core.common.utils.Constants.INFO_KEY
import com.arabam.android.assignment.core.model.DetailAdvert
import com.arabam.android.assignment.feature.details.model.info.getInfoList
import com.arabam.android.assignment.feature.details.tabs.DescriptionFragment
import com.arabam.android.assignment.feature.details.tabs.InfoFragment

class DetailsFragmentPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val fragments: List<Fragment>,
    private val advert: DetailAdvert,
    private val parentFragment: Fragment
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    init {
        registerFragmentTransactionCallback(object :
                FragmentTransactionCallback() {
                override fun onFragmentMaxLifecyclePreUpdated(
                    fragment: Fragment,
                    maxLifecycleState: Lifecycle.State,
                ) = if (maxLifecycleState == Lifecycle.State.RESUMED) {
                    OnPostEventListener {
                        fragment.parentFragmentManager.commitNow {
                            setPrimaryNavigationFragment(fragment)
                        }
                    }
                } else {
                    super.onFragmentMaxLifecyclePreUpdated(fragment, maxLifecycleState)
                }
            })
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        val fragment = fragments[position]
        when (fragment) {
            is DescriptionFragment -> {
                fragment.arguments = Bundle().apply {
                    putString(DESCRIPTION_KEY, advert.text)
                }
            }
            is InfoFragment -> {
                fragment.arguments = Bundle().apply {
                    val infoList = getInfoList(advert, parentFragment.requireContext())
                    putParcelableArray(INFO_KEY, infoList.toTypedArray())
                }
            }
        }
        return fragment
    }
}
