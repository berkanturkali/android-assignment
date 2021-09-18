package com.arabam.android.assigment.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assigment.data.model.sort.SortItem
import com.arabam.android.assigment.databinding.SortItemBinding
import javax.inject.Inject

class SortAdapter @Inject constructor() : ListAdapter<SortItem, SortAdapter.ViewHolder>(SORT_COMPARATOR){

    companion object{
        val SORT_COMPARATOR =  object : DiffUtil.ItemCallback<SortItem>() {
            override fun areItemsTheSame(oldItem: SortItem, newItem: SortItem): Boolean =
                oldItem.type == newItem.type

            override fun areContentsTheSame(oldItem: SortItem, newItem: SortItem): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortAdapter.ViewHolder =
        ViewHolder(
            SortItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: SortAdapter.ViewHolder, position: Int) {
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

                    }
                }
            }
        }

        fun bind(item: SortItem) {
            binding.item = item
        }
    }
}