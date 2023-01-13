package com.arabam.android.assignment.feature.details.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arabam.android.assignment.core.common.utils.inflate
import com.arabam.android.assignment.core.model.ListingAdvert
import com.arabam.android.assignment.feature.details.R
import com.arabam.android.assignment.feature.details.databinding.LastVisitedItemBinding
import javax.inject.Inject

class LastVisitedItemsAdapter @Inject constructor(private val circularProgressDrawable: CircularProgressDrawable) :
    ListAdapter<ListingAdvert, RecyclerView.ViewHolder>(ADVERT_COMPARATOR) {

    companion object {
        val ADVERT_COMPARATOR = object : DiffUtil.ItemCallback<ListingAdvert>() {
            override fun areItemsTheSame(oldItem: ListingAdvert, newItem: ListingAdvert): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ListingAdvert,
                newItem: ListingAdvert,
            ): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        LastVisitedItemsViewHolder(
            LastVisitedItemBinding.bind(parent.inflate(R.layout.last_visited_item))
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            (holder as LastVisitedItemsViewHolder).bind(it)
        }
    }

    inner class LastVisitedItemsViewHolder(private val binding: LastVisitedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(advert: ListingAdvert) {
            binding.advert = advert
            binding.progressDrawable = circularProgressDrawable
        }
    }
}
