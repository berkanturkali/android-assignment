package com.arabam.android.assignment.feature.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arabam.android.assignment.core.common.databinding.AdvertItemBinding
import com.arabam.android.assignment.core.model.ItemClickListener
import com.arabam.android.assignment.core.model.ListingAdvert
import javax.inject.Inject

class FavoritesAdapter @Inject constructor(
    private val circularProgressDrawable: CircularProgressDrawable,
) : ListAdapter<ListingAdvert, FavoritesAdapter.ViewHolder>(ADVERT_COMPARATOR) {

    private lateinit var listener: ItemClickListener<ListingAdvert>

    companion object {
        val ADVERT_COMPARATOR = object : DiffUtil.ItemCallback<ListingAdvert>() {
            override fun areItemsTheSame(oldItem: ListingAdvert, newItem: ListingAdvert): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ListingAdvert,
                newItem: ListingAdvert,
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdvertItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val advert = getItem(position)
        advert?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: AdvertItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val advert = getItem(position)
                    advert?.let {
                        listener.onClick(it)
                    }
                }
            }
        }

        fun bind(item: ListingAdvert) {
            binding.apply {
                advert = item
                progressDrawable = circularProgressDrawable
            }
        }
    }

    fun setListener(listener: ItemClickListener<ListingAdvert>) {
        this.listener = listener
    }
}
