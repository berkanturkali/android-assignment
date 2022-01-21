package com.arabam.android.assignment.listing.adapter.viewholder

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assignment.commons.utils.inflate
import com.arabam.android.assignment.listing.R
import com.arabam.android.assignment.listing.databinding.AdvertLoadStateFooterItemLayoutBinding

class AdvertLoadStateViewHolder(
    private val binding: AdvertLoadStateFooterItemLayoutBinding,
    retry: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.retryButton.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): AdvertLoadStateViewHolder {
            val view =
                AdvertLoadStateFooterItemLayoutBinding.bind(parent.inflate(R.layout.advert_load_state_footer_item_layout))
            return AdvertLoadStateViewHolder(view, retry)
        }
    }
}
