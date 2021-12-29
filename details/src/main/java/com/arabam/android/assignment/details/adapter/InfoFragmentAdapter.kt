package com.arabam.android.assignment.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assignment.details.R
import com.arabam.android.assignment.details.databinding.InfoWithTextItemBinding
import com.arabam.android.assignment.details.databinding.InfoWithTitleItemBinding
import com.arabam.android.assignment.details.model.info.Info
import javax.inject.Inject

class InfoFragmentAdapter
@Inject constructor() : ListAdapter<Info, RecyclerView.ViewHolder>(INFO_COMPARATOR) {

    companion object {
        val INFO_COMPARATOR = object : DiffUtil.ItemCallback<Info>() {
            override fun areItemsTheSame(oldItem: Info, newItem: Info) =
                (oldItem is Info.InfoWithText && newItem is Info.InfoWithText && oldItem.text == newItem.text) ||
                        (oldItem is Info.InfoWithTitle && newItem is Info.InfoWithTitle && oldItem.title == newItem.title)

            override fun areContentsTheSame(oldItem: Info, newItem: Info) =
                oldItem == newItem
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.info_with_text_item -> {
                InfoWithTextViewHolder(InfoWithTextItemBinding.inflate(LayoutInflater.from(
                    parent.context), parent, false))
            }
            R.layout.info_with_title_item -> {
                InfoWithTitleViewHolder(InfoWithTitleItemBinding.inflate(LayoutInflater.from(parent.context),
                    parent,
                    false))
            }
            else -> throw Exception("no type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            when (item) {
                is Info.InfoWithText -> {
                    (holder as InfoWithTextViewHolder).bind(it as Info.InfoWithText)
                }
                is Info.InfoWithTitle -> {
                    (holder as InfoWithTitleViewHolder).bind(it as Info.InfoWithTitle)
                }
            }
        }
    }

    inner class InfoWithTextViewHolder(private val binding: InfoWithTextItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(info: Info.InfoWithText) {
            binding.info = info
        }
    }

    inner class InfoWithTitleViewHolder(private val binding: InfoWithTitleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(info: Info.InfoWithTitle) {
            binding.info = info
            binding.position = absoluteAdapterPosition
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Info.InfoWithText -> {
                R.layout.info_with_text_item
            }
            is Info.InfoWithTitle -> {
                R.layout.info_with_title_item
            }
            else -> throw Exception("no type")
        }
    }
}