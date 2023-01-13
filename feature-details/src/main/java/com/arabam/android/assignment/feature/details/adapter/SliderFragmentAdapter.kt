package com.arabam.android.assignment.feature.details.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arabam.android.assignment.core.common.utils.inflate
import com.arabam.android.assignment.feature.details.R
import com.arabam.android.assignment.feature.details.databinding.ViewPagerItemBinding
import javax.inject.Inject

class SliderFragmentAdapter @Inject constructor(
    private val circularProgressDrawable: CircularProgressDrawable,
) :
    ListAdapter<String, RecyclerView.ViewHolder>(URL_COMPARATOR) {

    companion object {
        val URL_COMPARATOR = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        SliderViewHolder(ViewPagerItemBinding.bind(parent.inflate(R.layout.view_pager_item)))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            (holder as SliderViewHolder).bind(it)
        }
    }

    inner class SliderViewHolder(private val binding: ViewPagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) {
            binding.url = url
            binding.progressDrawable = circularProgressDrawable
        }
    }
}
