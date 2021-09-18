package com.arabam.android.assigment.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assigment.data.model.ListingAdvert
import com.arabam.android.assigment.databinding.AdvertItemBinding

import javax.inject.Inject


class ListingAdapter @Inject constructor(
) :
    PagingDataAdapter<ListingAdvert, ListingAdapter.AdvertViewHolder>(AdvertComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AdvertViewHolder(
            AdvertItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: AdvertViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class AdvertViewHolder(private val binding: AdvertItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    item?.let {

                    }
                }
            }
        }

        fun bind(item: ListingAdvert) {
            with(binding) {
                advert = item
            }
        }
    }

    object AdvertComparator : DiffUtil.ItemCallback<ListingAdvert>() {
        override fun areItemsTheSame(oldItem: ListingAdvert, newItem: ListingAdvert) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ListingAdvert, newItem: ListingAdvert) =
            oldItem == newItem
    }
}