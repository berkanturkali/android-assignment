package com.arabam.android.assignment.listing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assignment.listing.databinding.SortItemBinding
import com.arabam.android.assignment.listing.model.sort.SortItem
import com.arabam.android.assignment.model.ItemClickListener
import javax.inject.Inject

class SortAdapter @Inject constructor() :
    ListAdapter<SortItem, SortAdapter.ViewHolder>(SORT_COMPARATOR) {

    private lateinit var listener: ItemClickListener<SortItem>

    companion object {
        val SORT_COMPARATOR = object : DiffUtil.ItemCallback<SortItem>() {
            override fun areItemsTheSame(oldItem: SortItem, newItem: SortItem): Boolean =
                oldItem.type == newItem.type

            override fun areContentsTheSame(oldItem: SortItem, newItem: SortItem): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            SortItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: SortItemBinding) :
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

        fun bind(item: SortItem) {
            binding.item = item
        }
    }

    fun setClickListener(listener: ItemClickListener<SortItem>) {
        this.listener = listener
    }
}
