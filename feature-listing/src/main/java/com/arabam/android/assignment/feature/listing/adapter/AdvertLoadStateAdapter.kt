package com.arabam.android.assignment.feature.listing.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.arabam.android.assignment.feature.listing.adapter.viewholder.AdvertLoadStateViewHolder

class AdvertLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<AdvertLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: AdvertLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ): AdvertLoadStateViewHolder =
        AdvertLoadStateViewHolder.create(parent, retry)
}
