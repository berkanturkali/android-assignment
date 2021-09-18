package com.arabam.android.assigment.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arabam.android.assigment.data.model.DetailAdvert
import com.arabam.android.assigment.utils.Constants.ADVERT_KEY

class DetailsFragmentPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val fragments: List<Fragment>,
    private val advert: DetailAdvert,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    init {
        registerFragmentTransactionCallback(object :
            FragmentStateAdapter.FragmentTransactionCallback() {
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
        fragment.arguments = Bundle().apply {
            putParcelable(ADVERT_KEY, advert)
        }
        return fragment
    }
}