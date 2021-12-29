package com.arabam.android.assignment.listing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arabam.android.assignment.commons.databinding.AdvertItemBinding
import com.arabam.android.assignment.listing.databinding.GridAdvertItemLayoutBinding
import com.arabam.android.assignment.listing.model.ItemClickListener
import com.arabam.android.assignment.listing.model.ListingAdvert
import javax.inject.Inject


class ListingAdapter @Inject constructor(
    private val circularProgressDrawable: CircularProgressDrawable,
) :
    PagingDataAdapter<ListingAdvert, RecyclerView.ViewHolder>(AdvertComparator) {

    private var isGridMode = false

    private lateinit var listener: ItemClickListener<ListingAdvert>

    companion object {
        const val GRID = 0
        const val LINEAR = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == LINEAR) {
            AdvertViewHolder(
                AdvertItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        } else {
            GridAdvertViewHolder(GridAdvertItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (!isGridMode) {
            getItem(position)?.let { (holder as AdvertViewHolder).bind(it) }
        } else {
            getItem(position)?.let { (holder as GridAdvertViewHolder).bind(it) }
        }
    }

    inner class AdvertViewHolder(private val binding: AdvertItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    item?.let {
                        listener.onClick(it)
                    }
                }
            }
        }

        fun bind(item: ListingAdvert) {
            with(binding) {
                advert = item
                progressDrawable = circularProgressDrawable
            }
        }
    }

    inner class GridAdvertViewHolder(private val binding: GridAdvertItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    item?.let {
                        listener.onClick(it)
                    }
                }
            }
        }

        fun bind(item: ListingAdvert) {
            with(binding) {
                advert = item
                progressDrawable = circularProgressDrawable
            }
        }
    }

    fun setListener(listener: ItemClickListener<ListingAdvert>) {
        this.listener = listener
    }

    fun setGridMode(isGridMode: Boolean) {
        this.isGridMode = isGridMode
    }

    object AdvertComparator : DiffUtil.ItemCallback<ListingAdvert>() {
        override fun areItemsTheSame(oldItem: ListingAdvert, newItem: ListingAdvert) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ListingAdvert, newItem: ListingAdvert) =
            oldItem == newItem
    }

    override fun getItemViewType(position: Int): Int {
        return if (isGridMode) {
            GRID
        } else {
            LINEAR
        }
    }
}