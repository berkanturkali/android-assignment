package com.arabam.android.assigment.ui.adapters.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assigment.R
import com.arabam.android.assigment.databinding.AdvertLoadStateFooterItemLayoutBinding

class AdvertLoadStateViewHolder(
    private val binding: AdvertLoadStateFooterItemLayoutBinding,
    retry: () -> Unit,
):RecyclerView.ViewHolder(binding.root) {
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
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.advert_load_state_footer_item_layout, parent, false)
            val binding = DataBindingUtil.bind<AdvertLoadStateFooterItemLayoutBinding>(view)
            return AdvertLoadStateViewHolder(binding!!, retry)
        }
    }
}